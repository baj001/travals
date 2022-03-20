package com.baizhi.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author sfkstart
 * @create 2021-11-25-21:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//链式调用
@Accessors(chain = true)
public class Result {
    private Boolean state = true;
    private String msg;
    private String userId;
}
