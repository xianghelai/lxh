package com.lai.shirotags.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lai.shirotags.entity.TableUser;
import com.lai.shirotags.mapper.TableUserMapper;
import com.lai.shirotags.request.ConditionVo;
import com.lai.shirotags.request.Mypage;
import com.lai.shirotags.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author bbdw
 * @date 2019/10/8 11:41
 */
@Controller
public class UserController {
    @Autowired
    TableUserMapper tableUserMapper;
    private Logger logger =  LoggerFactory.getLogger(UserController.class);

    /**
     * 用户注册
     * @param user
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String insertUser(TableUser user,Model model){
        /*TableUser user = new TableUser();
        user.setBirthday(new Date());
        user.setContactAddress("dsioa");
        user.setContactEmail("1059653473@qq.com");
        user.setGender("1");
        user.setLoginName("csair");
        user.setPostCode("34722");
        user.setLoginPassword(Md5Util.md5("45721"));
        user.setUserName("lailai");
        user.setUserStatus("1");
        user.setRegisterTime(new Date());*/
        user.setLoginPassword(Md5Util.md5(user.getLoginPassword()));
        TableUser dbUser = tableUserMapper.findByLoginName(user.getLoginName());
        if(dbUser!=null){
            logger.error("注册：用户已存在:"+user.getLoginName());
            model.addAttribute("msg","注册：用户已存在");
            return "forward:error/注册：用户已存在";
        }else {
            tableUserMapper.insert(user);
        }
        return "login";
    }
    @PostMapping("/selectBy")
    @ResponseBody
    public TableUser selectByUser(){
        TableUser tableUser = tableUserMapper.selectByPrimaryKey(3);
        System.out.println(tableUser);
        return tableUser;
    }

    /**
     * 条件查询用户
     * @param myPage
     * @return
     */
    @PostMapping("/selectByConditions")
    @ResponseBody
    public JSONObject selectByConditions( @RequestBody Mypage myPage){
        /*,@RequestBody Mypage myPage*/
        //Mypage myPage = conditionVo.getMypage();
        ConditionVo conditionVo = myPage.getConditionVo();
        if(myPage!=null&&myPage.getOffset()!=null&&myPage.getLimit()!=null){
            PageHelper.startPage(myPage.getOffset()/myPage.getLimit()+1,myPage.getLimit());
        }else {
            PageHelper.startPage(1, 10);
        }
        List<TableUser> list;
        if(conditionVo!=null) {
             list = tableUserMapper.selectByConditions(conditionVo);
        }
        else{
             list = tableUserMapper.selectByConditions(new ConditionVo());
        }
        PageInfo<TableUser> pageInfo = new PageInfo<>(list);
        List<TableUser> pageInfoList = pageInfo.getList();
        JSONObject result = new JSONObject();
        result.put("rows",pageInfoList);
        result.put("total",pageInfo.getTotal());
        System.out.println(list);
        return result;

    }
    @PostMapping("/selectByConditions2")
    @ResponseBody
    public JSONObject selectByConditions2(@RequestBody Mypage myPage){
        /*,@RequestBody Mypage myPage*/
        //Mypage myPage = conditionVo.getMypage();
        ConditionVo conditionVo = myPage.getConditionVo();
        PageHelper.startPage(myPage.getOffset(),myPage.getLimit());
        List<TableUser> list =null;
        if(conditionVo!=null) {
            list = tableUserMapper.selectByConditions(conditionVo);
        }
        PageInfo<TableUser> pageInfo = new PageInfo<>(list);
        List<TableUser> pageInfoList = pageInfo.getList();
        JSONObject result = new JSONObject();
        result.put("rows",pageInfoList);
        result.put("total",pageInfo.getTotal());
        System.out.println(list);
        return result;

    }
    @PostMapping("/update")
    public String updateUserInfo(TableUser user,Model model){
        if(user.getLoginPassword()!=null&&user.getLoginPassword()!=""){
        user.setLoginPassword(Md5Util.md5(user.getLoginPassword()));
        }
        int i = tableUserMapper.updateByPrimaryKey(user);
        if(1==i) {
            return "redirect:userInfo2";
        }else{
            model.addAttribute("msg","更新错误");
            return "forward:error/更新错误";
        }
    }

    /**
     * 删除用户数据
     * @param userId
     * @return
     */
    @PostMapping("/delUser")
    @ResponseBody
    public void delById(String userId, HttpServletResponse response) throws Exception {
        TableUser tableUser = tableUserMapper.selectByPrimaryKey(Integer.parseInt(userId));
        JSONObject object = new JSONObject();
        PrintWriter out = response.getWriter();
        if(tableUser!=null){
            //判断用户是否处于禁用状态
            if("1".equals(tableUser.getUserStatus())){
                logger.error("删除失败：用户未处于禁用状态");
                object.put("msg","error");
            }else{
                int i = tableUserMapper.deleteByPrimaryKey(Integer.parseInt(userId));
                if(i==1){
                    logger.info("id为："+userId+"的用户已删除成功");
                }
                object.put("msg","success");
            }
        }
        out.write(object.toString());
    }

    /**
     * 跳转登陆页
     * @param model
     * @return
     */
    @GetMapping("login")
    public String toLogin(Model model){
        model.addAttribute("name","laixianghe");
        model.addAttribute("msg","freemarker取值");
        logger.info("跳转登陆页：");
        return "login";
    }

    /**
     * 登陆
     * @param user
     * @param model
     * @return
     */
    @PostMapping("dologin")
    public String login(TableUser user,Model model){
        System.out.println(user);
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),Md5Util.md5(user.getLoginPassword()));
        //执行登陆方法
        try {
            subject.login(token);
            System.out.println("登陆成功");
            //登陆成功跳转到首页 重定向 不带数据返回
            return "redirect:/index";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg","登陆失败，密码错误");
            System.out.println("登陆失败");
            return "forward:/login";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","登陆失败,用户名不存在");
            return "forward:/login";
        }

    }

    /**
     * 登出
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "login";
    }

    /**
     * 跳转首页
     * @param
     * @return
     */
    @GetMapping("index")
    public String toIndex(){
        /*model.addAttribute("name","laixianghe");
        model.addAttribute("msg","freemarker取值");*/
        return "index";
    }
    /**
     * 跳转用户列表页
     * @param
     * @return
     */
    @GetMapping("/userInfo")
    public String toUserinfo(ConditionVo conditionVo,Model model,Integer pageNum){
        model.addAttribute("name","laixianghe");
        model.addAttribute("msg","freemarker取值");
        PageHelper.startPage(1,5);
        List<TableUser> list = tableUserMapper.selectByConditions(conditionVo);
        PageInfo<TableUser> pageInfo = new PageInfo<>(list);
        List<TableUser> pageInfoList = pageInfo.getList();
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        model.addAttribute("list",pageInfoList);
        return "userInfo/userList";
    }
    /**
     * 跳转用户列表页
     * @param
     * @return
     */
    @GetMapping("/userInfo2")
    public String toUserinfo2(ConditionVo conditionVo,Model model){
        model.addAttribute("name","laixianghe");
        model.addAttribute("msg","freemarker取值");
        PageHelper.startPage(1,5);
        List<TableUser> list = tableUserMapper.selectByConditions(conditionVo);
        PageInfo<TableUser> pageInfo = new PageInfo<>(list);
        List<TableUser> pageInfoList = pageInfo.getList();
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        model.addAttribute("list",pageInfoList);
        return "userInfo/userList2";
    }
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }

    @GetMapping("/error/{msg}")
    public String toError(@PathVariable("msg") String msg, Model model)throws Exception{
        model.addAttribute("msg",msg);
        return "error";
    }
    @GetMapping("unauthorization")
    public String unanthorization(){
        return "unauthorization";
    }
}
