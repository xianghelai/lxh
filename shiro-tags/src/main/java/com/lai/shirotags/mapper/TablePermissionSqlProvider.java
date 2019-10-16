package com.lai.shirotags.mapper;


import com.lai.shirotags.entity.TablePermission;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class TablePermissionSqlProvider {

    public String insertSelective(TablePermission record) {
        BEGIN();
        INSERT_INTO("table_permission");
        
        if (record.getPid() != null) {
            VALUES("pid", "#{pid,jdbcType=INTEGER}");
        }
        
        if (record.getpName() != null) {
            VALUES("p_name", "#{pName,jdbcType=VARCHAR}");
        }
        
        if (record.getpUrl() != null) {
            VALUES("p_url", "#{pUrl,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(TablePermission record) {
        BEGIN();
        UPDATE("table_permission");
        
        if (record.getpName() != null) {
            SET("p_name = #{pName,jdbcType=VARCHAR}");
        }
        
        if (record.getpUrl() != null) {
            SET("p_url = #{pUrl,jdbcType=VARCHAR}");
        }
        
        WHERE("pid = #{pid,jdbcType=INTEGER}");
        
        return SQL();
    }
}