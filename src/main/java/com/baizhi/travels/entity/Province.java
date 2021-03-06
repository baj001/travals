package com.baizhi.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author sfkstart
 * @create 2022-01-19-16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class Province {
     private String id;
     private String name;
     private String tags;
     private Integer placecounts;
}
