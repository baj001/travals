package com.baizhi.travels.controller;

import java.util.ArrayList;

/**
 * @author sfkstart
 * @create 2022-01-10-20:32
 */
public class GCUseTest {
    public static void main(String[] args)  {
        ArrayList<byte[]> list = new ArrayList<>();

// git修改测试
        while(true){
            //定义一个arr数组长度为100 将list添加到arr中
            byte[] arr = new byte[100];
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
