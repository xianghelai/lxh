package com.lai.shirotags.service.serviceImpl;

import com.lai.shirotags.entity.TablePermission;
import com.lai.shirotags.mapper.TablePermissionMapper;
import com.lai.shirotags.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bbdw
 * @date 2019/10/14 16:24
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private TablePermissionMapper tablePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return tablePermissionMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int insert(TablePermission record) {
        return tablePermissionMapper.insert(record);
    }

    @Override
    public TablePermission selectByPrimaryKey(Integer pid) {
        return tablePermissionMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateByPrimaryKey(TablePermission record) {
        return tablePermissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TablePermission> selectPermissionsByUserId(Integer uid) {
        return tablePermissionMapper.selectPermissionsByUserId(uid);
    }
}
