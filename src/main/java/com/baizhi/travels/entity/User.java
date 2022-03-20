package com.baizhi.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author sfkstart
 * @create 2021-11-25-19:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {

    private String id;
    private String username;
    private String password;
    private String email;
}
