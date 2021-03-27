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

    // �洢Ԫ�صĵط�
    private T[] array;

    long p1, p2, p3, p4, p5, p6, p7;

    // дָ�룬�������֤����������Ϊvolatile
    private volatile long writeIndex;
    long p11, p12, p13, p14, p15, p16, p17;

    // ��ָ�룬ֻ��һ�������ߣ����Բ�Ҫ����volatile
    private long readIndex;
    long p21, p22, p23, p24, p25, p26, p27;


    // Ԫ�ظ����������ߺ������߶������޸ģ���������Ϊvolatile
    private volatile long size;
    long p31, p32, p33, p34, p35, p36, p37;

    // Unsafe����
    private static Unsafe UNSAFE;

    // �������ƫ����
    private static long ARRAY_BASE_OFFSET;

    // ����Ԫ��ƫ����
    private static long ARRAY_ELEMENT_OFFSET;

    // writeIndex��ƫ����
    private static long WRITE_INDEX_OFFSET;

    // readIndex��ƫ����
    private static long READ_INDEX_OFFSET;

    // size��ƫ����
    private static long SIZE_OFFSET;
    static {
        Field f = null;
        try{
            // ��ȡunsafe�� ʵ��
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);

            UNSAFE = (Unsafe) f.get(null);

            // �����������ƫ����
            ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(Object.class);

            // ����������Ԫ��ƫ����
            // �򵥵���⣬64λϵͳ���Ǹ���ѹ��ָ��ռ��4���ֽڣ�û��ѹ��ָ��ռ��8���ֽ�
            int scale = UNSAFE.arrayIndexScale(Object.class);

            if (4 == scale) {
                ARRAY_ELEMENT_OFFSET = 2;
            } else if (8 == scale) {
                ARRAY_ELEMENT_OFFSET = 3;
            }else {
                throw new IllegalArgumentException("δָ֪��Ĵ�С");
            }


            // ����writeIndex��ƫ����
            WRITE_INDEX_OFFSET = UNSAFE.objectFieldOffset(MpsArrayQueue.class.getDeclaredField("writeIndex"));

            READ_INDEX_OFFSET = UNSAFE.objectFieldOffset(MpsArrayQueue.class.getDeclaredField("readIndex"));

            // ����size��ƫ����
            SIZE_OFFSET = UNSAFE.objectFieldOffset(MpsArrayQueue.class.getDeclaredField("size"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public MpsArrayQueue(int capacity) {
        // ȡ����2��N�η���δ����Խ�磩
        capacity = 1 << (32 - Integer.numberOfLeadingZeros(capacity - 1));
        // ʵ��������
        this.array = (T[]) new Object[capacity];
    }


    // ����Ԫ��
    public boolean put(T t) {
        if (t == null) {
            return false;
        }
        long size;
        long writeIndex;
        do {
            // ÿ��ѭ����Ҫ���»�ȡsize�Ĵ�С
            size = this.size;
            if (size >= this.array.length)
                return false;

            // ÿ��ѭ�������»�ȡwriteIndex��ֵ
            writeIndex = this.writeIndex;

            // whileѭ����ԭ�Ӹ���writeIndex��ֵ
            // ���ʧ��������������Ĺ���
        } while (!UNSAFE.compareAndSwapLong(this, WRITE_INDEX_OFFSET, writeIndex, writeIndex + 1));


        // �����˵������ԭ�Ӹ��³ɹ�
        // ��ô�Ͱ�Ԫ�ص�ֵ�ŵ�writeIndex��λ��
        // �Ҹ���size
        long eleOffset = calcElementOffset(writeIndex, this.array.length - 1);

        // �ӳٸ��µ����ڴ棬��ȡ��ʱ��Ÿ���
        UNSAFE.putOrderedObject(this.array, eleOffset, t);

        // ����֪���ɹ�
        do {

            size = this.size;
        } while (!UNSAFE.compareAndSwapLong(this, SIZE_OFFSET, size, size + 1));
        return true;
    }


    public T take(){
        long size = this.size;
        if(size <= 0 )
            return null;

        // ֻ��һ�������ߣ���ͬ�����̰߳�ȫ������
        long readIndex = this.readIndex;
        // �����ָ��Ԫ�ص�ƫ����
        long offset = calcElementOffset(readIndex, this.array.length - 1);
        T e = (T) UNSAFE.getObjectVolatile(this.array, offset);


        // ���Ӷ�ָ��
        UNSAFE.putOrderedObject(this, READ_INDEX_OFFSET, offset);

        // ��Сsize
        do {
            size = this.size;
        } while (!UNSAFE.compareAndSwapLong(this, SIZE_OFFSET, size, size - 1));
        return e;
    }



    private long calcElementOffset(long index, long mask) {
        // index & mask �൱��ȡ��������ʾindex��������β�˴�ͷ��ʼ
        return ARRAY_BASE_OFFSET + ((index & mask) << ARRAY_ELEMENT_OFFSET);
    }
}
