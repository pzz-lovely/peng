package com.peng;

/**
 * @Author lovely
 * @Create 2021-01-21 14:14
 * @Description
 */
public class PathDemo {
    public static void main(String[] args) {
        String path = "api/user/export/1/10";
        System.out.println(path.substring(0,path.lastIndexOf("/export") + "/export".length()));
    }
}