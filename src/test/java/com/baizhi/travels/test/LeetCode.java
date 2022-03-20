package com.baizhi.travels.test;

import org.junit.Test;

/**
 * @author sfkstart
 * @create 2022-01-25-10:59
 */
public class LeetCode {
    //测试两个字符串相加


    @Test
    public void addtest(){
        String pre = "ab";
        String tmp = "bb";
        String res1 = pre + tmp;
        String res2 = tmp + pre;
        System.out.println(res1.toString());
        System.out.println(res2.toString());

    }
}
