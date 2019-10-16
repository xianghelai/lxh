package com.lai.shirotags.shiro;

import com.lai.shirotags.entity.TablePermission;
import com.lai.shirotags.entity.TableRole;
import com.lai.shirotags.entity.TableUser;
import com.lai.shirotags.service.PermissionService;
import com.lai.shirotags.service.RoleService;
import com.lai.shirotags.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by lxh on 2019/9/24
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm{
    
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给i资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //到数据库去查询当前登陆用户的授权字符串
        Subject subject = SecurityUtils.getSubject();
        TableUser user = (TableUser) subject.getPrincipal();
        TableUser dbuser = userService.findById(user.getUserId());
        List<TableRole> roles = roleService.selectRolesByUserId(user.getUserId());
        //通过set去重 得到role集合
        Set<String> roleset = roles.stream().map(TableRole::getRoleName).collect(Collectors.toSet());
        info.setRoles(roleset);

        List<TablePermission> permissions = permissionService.selectPermissionsByUserId(user.getUserId());
        Set<String> permissionset = permissions.stream().map(TablePermission::getpName).collect(Collectors.toSet());
        info.setStringPermissions(permissionset);
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库的用户名和密码
        //User user = new User("001", "xaingehla", "sjd2");
        //编写shiro判断逻辑，判断用户名 密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        TableUser user = userService.findByUsername(token.getUsername());
        //1.判断用户名
        if(user==null){
            //用户名不存在
            return null;
        }

        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getLoginPassword(),"");
    }
}
