package com.lai.shirotags.mapper;

import com.lai.shirotags.entity.TableRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TableRoleMapper {
    @Delete({
        "delete from table_role",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rid);

    @Insert({
        "insert into table_role (rid, role_name)",
        "values (#{rid,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR})"
    })
    int insert(TableRole record);

    @InsertProvider(type=TableRoleSqlProvider.class, method="insertSelective")
    int insertSelective(TableRole record);

    @Select({
        "select",
        "rid, role_name",
        "from table_role",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    TableRole selectByPrimaryKey(Integer rid);

    @UpdateProvider(type=TableRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableRole record);

    @Update({
        "update table_role",
        "set role_name = #{roleName,jdbcType=VARCHAR}",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableRole record);

    /**
     *根据uid拿到对应的所有角色
     * @param uid
     * @return
     */
    @Select({
            "SELECT r.* FROM user_role u_r",
            "LEFT JOIN table_user u ON u.User_Id=u_r.uid",
            "LEFT JOIN table_role r ON r.rid=u_r.rid",
            "WHERE uid=#{uid}"
    })
    @Results({
            @Result(column="rid", property="rid", jdbcType= JdbcType.INTEGER),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
    })
    List<TableRole> selectRolesByUserId(Integer uid);
}