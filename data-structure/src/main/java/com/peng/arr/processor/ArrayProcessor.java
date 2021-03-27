package com.peng.arr.processor;

import com.peng.exception.MemoryException;

/**
 * @author lovely
 * @create 2021-03-06 9:39
 * @description 处理器
 */
public class ArrayProcessor {
    /**
     * 处理索引
     * @param index 数组对应索引
     * @param offset 偏移量
     * @param length 数组长度
     * @param ckOffset 检查offset
     * @return {arr} arr[0] fromIndex arr[1] endIndex
     */
    public int[] process(int index, int offset, int length, boolean ckOffset){
        if (ckOffset) {
            if (offset % 8 != 0) {
                throw new MemoryException("offset data abnormal " + offset);
            }
        }
        int endIndex = offset * (index + 1);
        if (index > length - 1) {
            throw new MemoryException("array out of bounds " + index + " > " + length);
        }
        int fromIndex = endIndex - offset;
        return new int[]{fromIndex, endIndex};
    }
}