package com.lai.shirotags.service.serviceImpl;

import com.lai.shirotags.entity.TableUser;
import com.lai.shirotags.mapper.TableUserMapper;
import com.lai.shirotags.request.ConditionVo;
import com.lai.shirotags.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxh on 2019/9/24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TableUserMapper tableUserMapper;

    @Override
    public TableUser findByUsername(String loginName) {
        return tableUserMapper.findByLoginName(loginName);
    }

    @Override
    public TableUser findById(Integer uid) {
        return tableUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return tableUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(TableUser record) {
        return tableUserMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(TableUser record) {
        return tableUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TableUser> selectByConditions(ConditionVo conditionVo) {
        return tableUserMapper.selectByConditions(conditionVo);
    }
}
