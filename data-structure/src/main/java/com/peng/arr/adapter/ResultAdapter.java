package com.peng.arr.adapter;

import com.peng.exception.ClassTypeException;
import com.peng.exception.MemoryException;

import java.lang.reflect.Method;

/**
 * @author lovely
 * @create 2021-03-06 10:06
 * @description 结果适配器
 */
public class ResultAdapter<T> {


    private T t;

    private Method parseMethod;

    private Method toBinaryValueMethod;

    private int offset;

    public ResultAdapter(T t) {
        this.t = t;
        try {
            if (t.getClass() == Integer.class || t.getClass() == int.class) {
                parseMethod = t.getClass().getDeclaredMethod("parseInt", String.class, int.class);
                toBinaryValueMethod = t.getClass().getDeclaredMethod("toBinaryString", int.class);
                this.offset = 32;
            } else if (t.getClass() == Long.class || t.getClass() == long.class) {
                parseMethod = t.getClass().getDeclaredMethod("parseLong", String.class, long.class);
                toBinaryValueMethod = t.getClass().getDeclaredMethod("toBinaryString", long.class);
                this.offset = 64;
            }
        } catch (Exception e) {
            throw new ClassTypeException("类型强转失败", e);
        }
    }

    /**
     * 获取结果
     * @param value 具体值
     * @return
     */
    public T result(String value) {
        try {
            return (T) parseMethod.invoke(t, value, 10);
        } catch (Exception e) {
            throw new MemoryException("value is not Number Type", e);
        }
    }

    public <T> String binaryResult(T value) {
        try {
            return (String) toBinaryValueMethod.invoke(t, value);
        } catch (Exception e) {
            throw new MemoryException("value is not Number Type", e);
        }
    }

    /**
     * @return 返回偏移量
     */
    public int offset() {
        return offset;
    }
}