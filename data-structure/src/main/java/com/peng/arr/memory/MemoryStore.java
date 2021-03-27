package com.peng.arr.memory;

import com.peng.arr.adapter.ResultAdapter;
import com.peng.arr.processor.ArrayProcessor;

/**
 * @author lovely
 * @create 2021-03-06 9:40
 * @description 内存存储器
 */
public class MemoryStore<T extends Number> {

    private final int length;
    // 内存
    private final StringBuilder store;

    private final ArrayProcessor arrayProcessor;

    // 偏移量
    private final int offset;

    private final ResultAdapter<T> resultAdapter;

    public MemoryStore(ResultAdapter<T> resultAdapter, int length) {
        this.resultAdapter = resultAdapter;
        this.offset = resultAdapter.offset();
        this.arrayProcessor = new ArrayProcessor();
        arrayProcessor.process(0, offset, length, false);
        this.store = new StringBuilder();
        // 开辟空间
        for (int i = offset; i < offset * length; i += offset) {
            String substring = Integer.toBinaryString(1 << (offset - 1)).substring(1);
            store.append(substring).append(0);
        }

        this.length = length;
    }

    /**
     * 从内存中获取值
     *
     * @param index 截取的位置
     * @return
     */
    public String getBinaryValue(int index) {
        int[] process = arrayProcessor.process(index, offset, length, false);
        return store.substring(process[0], process[1]);
    }

    /**
     * 根据给定的类型，返回指定类型的值
     *
     * @param index 查找的索引
     * @return 指定类型的值
     */
    public T getValue(int index) {
        String binaryValue = getBinaryValue(index);
        return resultAdapter.result(binaryValue);
    }


    public void setValue(int index, T value) {
        int[] process = arrayProcessor.process(index, offset, length, true);
        String s = resultAdapter.binaryResult(value);
        store.replace(process[0], process[0] + s.length()
                , s);
    }


    public int getLength() {
        return length;
    }
}