package com.lai.shirotags.service;


import com.lai.shirotags.entity.TableUser;
import com.lai.shirotags.request.ConditionVo;

import java.util.List;

/**
 * Created by lxh on 2019/9/24
 */
public interface UserService {
    public TableUser findByUsername(String username);
    public TableUser findById(Integer uid);
    int deleteByPrimaryKey(Integer userId);
    int insert(TableUser record);
    int updateByPrimaryKey(TableUser record);
    List<TableUser> selectByConditions(ConditionVo conditionVo);
}
