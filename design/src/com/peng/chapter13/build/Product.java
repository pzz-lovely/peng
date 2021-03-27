package com.peng.chapter13.build;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther lovely
 * @Create 2020-04-29 8:43
 * @Description todo
 */
public class Product {
    private List<String> parts = new ArrayList<>();
    //添加部零件
    public void add(String part) {
        parts.add(part);
    }

    public void show(){
        System.out.println("产品构建：" + parts);
    }
}
