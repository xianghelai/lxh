package com.lai.shirotags.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bbdw
 * @date 2019/10/11 10:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mypage {
    private Integer limit;
    private Integer offset;
    private String order;
    private ConditionVo conditionVo;
}
