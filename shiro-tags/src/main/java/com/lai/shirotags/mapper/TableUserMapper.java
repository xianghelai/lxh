package com.lai.shirotags.mapper;

import com.lai.shirotags.entity.TableUser;
import com.lai.shirotags.request.ConditionVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TableUserMapper {
    @Delete({
        "delete from table_user",
        "where User_Id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into table_user (User_Id, Login_Name, ",
        "Login_Password, Gender, ",
        "Contact_phone, Contact_Email, ",
        "User_Name, Birthday, ",
        "Contact_address, Post_Code, ",
        "Register_Time, User_Status, ",
        "Province, Last_Modefy_Time)",
        "values (#{userId,jdbcType=INTEGER}, #{loginName,jdbcType=VARCHAR}, ",
        "#{loginPassword,jdbcType=VARCHAR}, #{gender,jdbcType=CHAR}, ",
        "#{contactPhone,jdbcType=VARCHAR}, #{contactEmail,jdbcType=VARCHAR}, ",
        "#{userName,jdbcType=VARCHAR}, #{birthday,jdbcType=DATE}, ",
        "#{contactAddress,jdbcType=VARCHAR}, #{postCode,jdbcType=VARCHAR}, ",
        "now(), #{userStatus,jdbcType=CHAR}, ",
        "#{province,jdbcType=VARCHAR}, #{lastModefyTime,jdbcType=TIMESTAMP})"
    })
    int insert(TableUser record);

    @InsertProvider(type=TableUserSqlProvider.class, method="insertSelective")
    int insertSelective(TableUser record);

    @Select({
        "select",
        "User_Id, Login_Name, Login_Password, Gender, Contact_phone, Contact_Email, User_Name, ",
        "Birthday, Contact_address, Post_Code, Register_Time, User_Status, Province, ",
        "Last_Modefy_Time",
        "from table_user",
        "where User_Id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="User_Id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Login_Name", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="Login_Password", property="loginPassword", jdbcType=JdbcType.VARCHAR),
        @Result(column="Gender", property="gender", jdbcType=JdbcType.CHAR),
        @Result(column="Contact_phone", property="contactPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="Contact_Email", property="contactEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="User_Name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="Birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="Contact_address", property="contactAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="Post_Code", property="postCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="Register_Time", property="registerTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="User_Status", property="userStatus", jdbcType=JdbcType.CHAR),
        @Result(column="Province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="Last_Modefy_Time", property="lastModefyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TableUser selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=TableUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableUser record);

    @Update({"<script>",
        "update table_user",
        "<set>" ,
          "<if test=\"loginName != null and loginName != ''\"> Login_Name = #{loginName,jdbcType=VARCHAR},</if>",
          "<if test=\"loginPassword != null and loginPassword != ''\">Login_Password = #{loginPassword,jdbcType=VARCHAR},</if>",
          "<if test=\"gender != null and gender != ''\">Gender = #{gender,jdbcType=CHAR},</if>",
          "<if test=\"contactPhone != null and contactPhone != ''\">Contact_phone = #{contactPhone,jdbcType=VARCHAR},</if>",
          "<if test=\"contactEmail != null and contactEmail != ''\">Contact_Email = #{contactEmail,jdbcType=VARCHAR},</if>",
          "<if test=\"userName != null and userName != ''\">User_Name = #{userName,jdbcType=VARCHAR},</if>",
          "<if test=\"birthday != null \">Birthday = #{birthday,jdbcType=DATE},</if>",
          "<if test=\"contactAddress != null and contactAddress != ''\">Contact_address = #{contactAddress,jdbcType=VARCHAR},</if>",
          "<if test=\"postCode != null and postCode != ''\">Post_Code = #{postCode,jdbcType=VARCHAR},</if>",
          "<if test=\"userStatus != null and userStatus != ''\">User_Status = #{userStatus,jdbcType=CHAR},</if>",
          "<if test=\"province != null and province != ''\">Province = #{province,jdbcType=VARCHAR},</if>",
          "Last_Modefy_Time = now()" ,
          "</set>",
        "where User_Id = #{userId,jdbcType=INTEGER}",
        "</script>"
    })
    int updateByPrimaryKey(TableUser record);

    @Select({"<script>",
            "select ",
            "User_Id, Login_Name, Login_Password, Gender, Contact_phone, Contact_Email, User_Name, ",
            "Birthday, Contact_address, Post_Code, Register_Time, User_Status, Province, ",
            "Last_Modefy_Time",
            "from table_user",
            "where 1=1" ,
            " <if test=\"c.loginName != null and c.loginName != ''\"> and Login_Name like CONCAT(CONCAT('%', #{c.loginName}), '%') </if>",
            " <if test=\"c.gender != null and c.gender != ''\"> and Gender = #{c.gender} </if>",
            " <if test=\"c.beginTime != null  and c.endTime != null \"> and Birthday between #{c.beginTime,jdbcType=DATE} and #{c.endTime,jdbcType=DATE} </if>",
            " <if test=\"c.userStatus != null and c.userStatus != ''\"> and User_Status = #{c.userStatus} </if>",
            " <if test=\"c.province != null and c.province != ''\"> and Province = #{c.province} </if>",
            " <if test=\"c.registerBeginTime != null  and c.registerEndTime != null \"> " ,
                    "and Register_Time between #{c.registerBeginTime,} and #{c.registerEndTime}</if>",
            "</script>"
    })
    @Results({
            @Result(column="User_Id", property="userId", jdbcType= JdbcType.VARCHAR),
            @Result(column="Login_Name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Login_Password", property="loginPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="Gender", property="gender", jdbcType=JdbcType.VARCHAR),
            @Result(column="Contact_phone", property="contactPhone", jdbcType=JdbcType.VARCHAR),
            @Result(column="Contact_Email", property="contactEmail", jdbcType=JdbcType.VARCHAR),
            @Result(column="User_Name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="Contact_address", property="contactAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="Post_Code", property="postCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="Register_Time", property="registerTime", jdbcType=JdbcType.DATE),
            @Result(column="User_Status", property="userStatus", jdbcType=JdbcType.VARCHAR),
            @Result(column="Province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="Last_Modefy_Time", property="lastModefyTime", jdbcType=JdbcType.DATE),
    })
    List<TableUser> selectByConditions(@Param("c") ConditionVo conditionVo);
    @Select({
            "select",
            "User_Id, Login_Name, Login_Password, Gender, Contact_phone, Contact_Email, User_Name, ",
            "Birthday, Contact_address, Post_Code, Register_Time, User_Status, Province, ",
            "Last_Modefy_Time",
            "from table_user",
            "where Login_Name = #{Login_Name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="User_Id", property="userId", jdbcType= JdbcType.VARCHAR),
            @Result(column="Login_Name", property="loginName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Login_Password", property="loginPassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="Gender", property="gender", jdbcType=JdbcType.VARCHAR),
            @Result(column="Contact_phone", property="contactPhone", jdbcType=JdbcType.VARCHAR),
            @Result(column="Contact_Email", property="contactEmail", jdbcType=JdbcType.VARCHAR),
            @Result(column="User_Name", property="userName", jdbcType=JdbcType.VARCHAR),
            @Result(column="Birthday", property="birthday", jdbcType=JdbcType.DATE),
            @Result(column="Contact_address", property="contactAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="Post_Code", property="postCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="Register_Time", property="registerTime", jdbcType=JdbcType.DATE),
            @Result(column="User_Status", property="userStatus", jdbcType=JdbcType.VARCHAR),
            @Result(column="Province", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="Last_Modefy_Time", property="lastModefyTime", jdbcType=JdbcType.DATE),
    })
    TableUser findByLoginName(String loginName);
}