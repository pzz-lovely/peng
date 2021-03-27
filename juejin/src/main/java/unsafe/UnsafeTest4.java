package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

class OffHeapArray{
    //一个int等于4个字节
    private static final int INT = 4;
    private long size;      //元素个数
    private long address;   //地址
    private static Unsafe unsafe;
    static {
        try{
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    //构造方法，分配内存
    public OffHeapArray(long size) {
        this.size = size;
        //参数字节数
        address = unsafe.allocateMemory(size * INT);
    }

    //获取指定索引处的元素
    public int get(long index) {
        return unsafe.getInt(address + index * INT);
    }

    //设置指定索引处的元素
    public void set(long index, int value) {
        unsafe.putInt(address + index * INT,value);
    }
    //元素个数
    public long size(){
        return size;
    }
    //释放对外内存
    public void freeMemory(){
        unsafe.freeMemory(address);
    }

}

public class UnsafeTest4 {
    public static void main(String[] args) {
        OffHeapArray offHeapArray = new OffHeapArray(4);
        offHeapArray.set(0, 1);
        offHeapArray.set(1, 2);
        offHeapArray.set(2, 3);
        offHeapArray.set(3, 4);
        offHeapArray.set(2, 5);//在索引2的位置重复放入元素
        int sum = 0 ;
        for (int i = 0; i < offHeapArray.size(); i++) {
            sum += offHeapArray.get(i);
        }
        System.out.println(sum);
        offHeapArray.freeMemory();
    }
}
