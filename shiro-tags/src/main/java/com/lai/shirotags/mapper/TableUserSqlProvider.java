package com.lai.shirotags.mapper;


import com.lai.shirotags.entity.TableUser;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class TableUserSqlProvider {

    public String insertSelective(TableUser record) {
        BEGIN();
        INSERT_INTO("table_user");
        
        if (record.getUserId() != null) {
            VALUES("User_Id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getLoginName() != null) {
            VALUES("Login_Name", "#{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginPassword() != null) {
            VALUES("Login_Password", "#{loginPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            VALUES("Gender", "#{gender,jdbcType=CHAR}");
        }
        
        if (record.getContactPhone() != null) {
            VALUES("Contact_phone", "#{contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getContactEmail() != null) {
            VALUES("Contact_Email", "#{contactEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            VALUES("User_Name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            VALUES("Birthday", "#{birthday,jdbcType=DATE}");
        }
        
        if (record.getContactAddress() != null) {
            VALUES("Contact_address", "#{contactAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getPostCode() != null) {
            VALUES("Post_Code", "#{postCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisterTime() != null) {
            VALUES("Register_Time", "#{registerTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserStatus() != null) {
            VALUES("User_Status", "#{userStatus,jdbcType=CHAR}");
        }
        
        if (record.getProvince() != null) {
            VALUES("Province", "#{province,jdbcType=VARCHAR}");
        }
        
        if (record.getLastModefyTime() != null) {
            VALUES("Last_Modefy_Time", "#{lastModefyTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(TableUser record) {
        BEGIN();
        UPDATE("table_user");
        
        if (record.getLoginName() != null) {
            SET("Login_Name = #{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginPassword() != null) {
            SET("Login_Password = #{loginPassword,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            SET("Gender = #{gender,jdbcType=CHAR}");
        }
        
        if (record.getContactPhone() != null) {
            SET("Contact_phone = #{contactPhone,jdbcType=VARCHAR}");
        }
        
        if (record.getContactEmail() != null) {
            SET("Contact_Email = #{contactEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            SET("User_Name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getBirthday() != null) {
            SET("Birthday = #{birthday,jdbcType=DATE}");
        }
        
        if (record.getContactAddress() != null) {
            SET("Contact_address = #{contactAddress,jdbcType=VARCHAR}");
        }
        
        if (record.getPostCode() != null) {
            SET("Post_Code = #{postCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRegisterTime() != null) {
            SET("Register_Time = #{registerTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserStatus() != null) {
            SET("User_Status = #{userStatus,jdbcType=CHAR}");
        }
        
        if (record.getProvince() != null) {
            SET("Province = #{province,jdbcType=VARCHAR}");
        }
        
        if (record.getLastModefyTime() != null) {
            SET("Last_Modefy_Time = #{lastModefyTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("User_Id = #{userId,jdbcType=INTEGER}");
        
        return SQL();
    }
}