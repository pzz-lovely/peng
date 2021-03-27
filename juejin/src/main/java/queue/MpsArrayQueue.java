package queue;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Auther lovely
 * @Create 2020-08-18 8:25
 * @Description todo
 */
public class MpsArrayQueue<T> {
    long p01, p02, p03, p04, p05, p06, p07;

    // 存储元素的地方
    private T[] array;

    long p1, p2, p3, p4, p5, p6, p7;

    // 写指针，多个生产证，所以声明为volatile
    private volatile long writeIndex;
    long p11, p12, p13, p14, p15, p16, p17;

    // 读指针，只有一个消费者，所以不要声明volatile
    private long readIndex;
    long p21, p22, p23, p24, p25, p26, p27;


    // 元素个数，生产者和消费者都可能修改，所以声明为volatile
    private volatile long size;
    long p31, p32, p33, p34, p35, p36, p37;

    // Unsafe变量
    private static Unsafe UNSAFE;

    // 数组基础偏移量
    private static long ARRAY_BASE_OFFSET;

    // 数组元素偏移量
    private static long ARRAY_ELEMENT_OFFSET;

    // writeIndex的偏移量
    private static long WRITE_INDEX_OFFSET;

    // readIndex的偏移量
    private static long READ_INDEX_OFFSET;

    // size的偏移量
    private static long SIZE_OFFSET;
    static {
        Field f = null;
        try{
            // 获取unsafe的 实例
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);

            UNSAFE = (Unsafe) f.get(null);

            // 计算数组基础偏移量
            ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(Object.class);

            // 计算数组中元素偏移量
            // 简单点理解，64位系统找那个有压缩指针占用4个字节，没有压缩指针占用8个字节
            int scale = UNSAFE.arrayIndexScale(Object.class);

            if (4 == scale) {
                ARRAY_ELEMENT_OFFSET = 2;
            } else if (8 == scale) {
                ARRAY_ELEMENT_OFFSET = 3;
            }else {
                throw new IllegalArgumentException("未知指针的大小");
            }


            // 计算writeIndex的偏移量
            WRITE_INDEX_OFFSET = UNSAFE.objectFieldOffset(MpsArrayQueue.class.getDeclaredField("writeIndex"));

            READ_INDEX_OFFSET = UNSAFE.objectFieldOffset(MpsArrayQueue.class.getDeclaredField("readIndex"));

            // 计算size的偏移量
            SIZE_OFFSET = UNSAFE.objectFieldOffset(MpsArrayQueue.class.getDeclaredField("size"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public MpsArrayQueue(int capacity) {
        // 取整的2的N次方（未考虑越界）
        capacity = 1 << (32 - Integer.numberOfLeadingZeros(capacity - 1));
        // 实例化数组
        this.array = (T[]) new Object[capacity];
    }


    // 生产元素
    public boolean put(T t) {
        if (t == null) {
            return false;
        }
        long size;
        long writeIndex;
        do {
            // 每次循环都要重新获取size的大小
            size = this.size;
            if (size >= this.array.length)
                return false;

            // 每次循环都重新获取writeIndex的值
            writeIndex = this.writeIndex;

            // while循环中原子更新writeIndex的值
            // 如果失败了重新做上面的过程
        } while (!UNSAFE.compareAndSwapLong(this, WRITE_INDEX_OFFSET, writeIndex, writeIndex + 1));


        // 到这里，说明上述原子更新成功
        // 那么就把元素的值放到writeIndex的位置
        // 且更新size
        long eleOffset = calcElementOffset(writeIndex, this.array.length - 1);

        // 延迟更新到主内存，读取的时候才更新
        UNSAFE.putOrderedObject(this.array, eleOffset, t);

        // 更新知道成功
        do {

            size = this.size;
        } while (!UNSAFE.compareAndSwapLong(this, SIZE_OFFSET, size, size + 1));
        return true;
    }


    public T take(){
        long size = this.size;
        if(size <= 0 )
            return null;

        // 只有一个消费者，不同考虑线程安全的问题
        long readIndex = this.readIndex;
        // 计算读指针元素的偏移量
        long offset = calcElementOffset(readIndex, this.array.length - 1);
        T e = (T) UNSAFE.getObjectVolatile(this.array, offset);


        // 增加读指针
        UNSAFE.putOrderedObject(this, READ_INDEX_OFFSET, offset);

        // 减小size
        do {
            size = this.size;
        } while (!UNSAFE.compareAndSwapLong(this, SIZE_OFFSET, size, size - 1));
        return e;
    }



    private long calcElementOffset(long index, long mask) {
        // index & mask 相当于取余数，表示index到达数组尾端从头开始
        return ARRAY_BASE_OFFSET + ((index & mask) << ARRAY_ELEMENT_OFFSET);
    }
}
