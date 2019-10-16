package com.lai.shirotags.service.serviceImpl;

import com.lai.shirotags.entity.TableRole;
import com.lai.shirotags.mapper.TableRoleMapper;
import com.lai.shirotags.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bbdw
 * @date 2019/10/14 16:22
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private TableRoleMapper tableRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer rid) {
        return tableRoleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public int insert(TableRole record) {
        return tableRoleMapper.insert(record);
    }

    @Override
    public TableRole selectByPrimaryKey(Integer rid) {
        return tableRoleMapper.selectByPrimaryKey(rid);
    }

    @Override
    public int updateByPrimaryKey(TableRole record) {
        return tableRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TableRole> selectRolesByUserId(Integer uid) {
        return tableRoleMapper.selectRolesByUserId(uid);
    }
}
