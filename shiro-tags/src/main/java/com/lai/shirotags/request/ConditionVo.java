package com.lai.shirotags.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author bbdw
 * @date 2019/10/8 14:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionVo {
    private String loginName;//支持模糊查询
    private String gender;//性别 提供下拉框 空值，男，女
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;//出生日期开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;//出生日期结束时间
    private String userStatus;//全部，启用，禁用
    private String province;//所属省份
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String registerBeginTime;//注册开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String registerEndTime;//注册结束时间
    private Mypage mypage;


}
