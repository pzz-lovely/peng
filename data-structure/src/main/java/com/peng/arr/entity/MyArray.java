package com.peng.arr.entity;

import com.peng.arr.adapter.ResultAdapter;
import com.peng.arr.memory.MemoryStore;

/**
 * @author lovely
 * @create 2021-03-06 9:35
 * @description
 */
public class MyArray<T extends Integer> implements IArray<T> {

    private MemoryStore<T> memoryStore;

    private ResultAdapter<T> resultAdapter;

    private T type;


    public MyArray(int length) {
        type = (T) Integer.valueOf(0);
        resultAdapter = new ResultAdapter<T>(type);
        resultAdapter.offset();
        this.memoryStore = new MemoryStore<T>(resultAdapter,length);
    }

    @Override
    public T get(int index) {
        return memoryStore.getValue(index);
    }

    @Override
    public void set(int index, T value) {
        memoryStore.setValue(index, value);
    }


    @Override
    public int length() {
        return memoryStore.getLength();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < memoryStore.getLength(); i++) {
            sb.append(memoryStore.getValue(i)).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}