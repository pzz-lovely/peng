package com.peng.jdk9.trygrammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author lovely
 * @create 2021-03-11 11:11
 * @description
 */
public class TryGrammarDemo {
    public static void main(String[] args) {

    }

    public void testTry1(){
        // jdk7 之前的代码
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(System.in);
            // 读取过程...
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 资源关闭操作
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void testTry2(){
        // jkd8 需要把实例化的过程放到入口参数中
        try (InputStreamReader reader = new InputStreamReader(System.in)) {
            // 读取数据的过程...
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void testTry3(){
        // jdk9，减少了很多冗余风格，编码也更健壮了
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try (reader; writer) {
            // 此时的reader是final，不可再被赋值
            // reader = null;
            // 读取数据的过程...
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}