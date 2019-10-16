package com.lai.shirotags.service;

import com.lai.shirotags.entity.TableRole;

import java.util.List;

/**
 * @author bbdw
 * @date 2019/10/14 16:19
 */
public interface RoleService {

    int deleteByPrimaryKey(Integer rid);
    int insert(TableRole record);
    TableRole selectByPrimaryKey(Integer rid);
    int updateByPrimaryKey(TableRole record);
    List<TableRole> selectRolesByUserId(Integer uid);
}
