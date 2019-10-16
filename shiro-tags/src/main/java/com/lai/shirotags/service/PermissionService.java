package com.lai.shirotags.service;

import com.lai.shirotags.entity.TablePermission;

import java.util.List;

/**
 * @author bbdw
 * @date 2019/10/14 16:20
 */
public interface PermissionService {
    int deleteByPrimaryKey(Integer pid);
    int insert(TablePermission record);
    TablePermission selectByPrimaryKey(Integer pid);
    int updateByPrimaryKey(TablePermission record);
    List<TablePermission> selectPermissionsByUserId(Integer uid);

}
