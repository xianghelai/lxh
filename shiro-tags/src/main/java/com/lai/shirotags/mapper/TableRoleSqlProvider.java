package com.lai.shirotags.mapper;


import com.lai.shirotags.entity.TableRole;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class TableRoleSqlProvider {

    public String insertSelective(TableRole record) {
        BEGIN();
        INSERT_INTO("table_role");
        
        if (record.getRid() != null) {
            VALUES("rid", "#{rid,jdbcType=INTEGER}");
        }
        
        if (record.getRoleName() != null) {
            VALUES("role_name", "#{roleName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(TableRole record) {
        BEGIN();
        UPDATE("table_role");
        
        if (record.getRoleName() != null) {
            SET("role_name = #{roleName,jdbcType=VARCHAR}");
        }
        
        WHERE("rid = #{rid,jdbcType=INTEGER}");
        
        return SQL();
    }
}