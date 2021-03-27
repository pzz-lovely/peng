package com.peng.test;

//import com.peng.jdk9.modeltest.TestModel;
import com.peng.jdk9.module.MyModel;

/**
 * @author lovely
 * @create 2021-03-11 10:35
 * @description
 */
public class NewModelTest {
    public static void main(String[] args) {
        MyModel myModel = new MyModel("0.0", "正方形");

//        TestModel testModel = new TestModel();
        System.out.println(myModel);
    }
}