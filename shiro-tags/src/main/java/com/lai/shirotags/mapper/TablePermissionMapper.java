package com.lai.shirotags.mapper;

import com.lai.shirotags.entity.TablePermission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface TablePermissionMapper {
    @Delete({
        "delete from table_permission",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer pid);

    @Insert({
        "insert into table_permission (pid, p_name, ",
        "p_url)",
        "values (#{pid,jdbcType=INTEGER}, #{pName,jdbcType=VARCHAR}, ",
        "#{pUrl,jdbcType=VARCHAR})"
    })
    int insert(TablePermission record);

    @InsertProvider(type=TablePermissionSqlProvider.class, method="insertSelective")
    int insertSelective(TablePermission record);

    @Select({
        "select",
        "pid, p_name, p_url",
        "from table_permission",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="p_name", property="pName", jdbcType=JdbcType.VARCHAR),
        @Result(column="p_url", property="pUrl", jdbcType=JdbcType.VARCHAR)
    })
    TablePermission selectByPrimaryKey(Integer pid);

    @UpdateProvider(type=TablePermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TablePermission record);

    @Update({
        "update table_permission",
        "set p_name = #{pName,jdbcType=VARCHAR},",
          "p_url = #{pUrl,jdbcType=VARCHAR}",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TablePermission record);

    /**
     * 根据用户id拿到用户对应的所有权限
     * @param uid
     * @return
     */
    @Select({
       "select p.* from role_permission r_p",
        "left join table_role r on r_p.rid=r.rid",
        "left join table_permission p on r_p.pid=p.pid",
        "LEFT JOIN user_role u_r ON r.rid = u_r.rid",
        "WHERE u_r.uid=#{uid}"
    })
    @Results({
            @Result(column="pid", property="pid", jdbcType= JdbcType.INTEGER),
            @Result(column="p_name", property="pName", jdbcType=JdbcType.VARCHAR),
            @Result(column="p_url", property="pUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<TablePermission> selectPermissionsByUserId(Integer uid);
}