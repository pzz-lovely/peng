# 内存映射文件

大多数操作系统都可以利用虚拟内存实现 来将一个文件或文件的一部分 "映射"到内存中。

## 通道

从文件中获取一个通道(channel)，通道是用于磁盘文件的一种抽象，它使我们可以访问诸如内存映射、文件加锁机制以及文件间快速传递数据等操作系统特性。

|              选项              |                             描述                             |
| :----------------------------: | :----------------------------------------------------------: |
| FileChannel.MapMode.Read_Only  | 所产生的缓冲区是只读的，任何对该缓冲区写入的尝试都会导致ReadOnlyBufferException异常 |
| FileChannel.MapMode.Read_WRITE | 所产生的的缓冲区是可写的，任何修改都会在某个时刻写回到文件中 |
|  FileChannel.MapMode.Private   | 所产生的缓冲区是可写的的，但是任何修改对这个缓冲区都是私有的，不会传播到文件中 |

~~~java
						/*获取FileChannel*/
java.io.InputStream
FileChannel getChannel()
//返回用于访问这个输入流的通道

java.io.OutputStream
FileChannel getChannel()
//返回用于访问这个输出流的通道

java.io.RandomAccessFile
FileChannel getChannel()
//返回用于访问这个文件的通道
~~~

~~~java
java.nio.channels.FileChannel 

static FileChannel open(Path path,OpenOption...options)
//打开指定路径的文件通道，默认情况下，通道打开时用于读入
	//Path 打开通道的文件所在路径 options StandardOpenOption枚举中的 Writer append Truncate_Existing create

MappedBytebuffer map(FileChannel.MapMode mode,long position,long size)
//将文件的一个区域映射到内存中
	//mode 上面的MapModl的常量， position 映射区域的起始位置 size 映射区域的大小
~~~


~~~java
				java.nio.Buffer

boolean hasRemaining()
//如果当前的缓冲区位置没没有到达这个缓冲区的界限位置，则返回true
int limit()
//返回这个缓冲区的界限位置，即没有任何值可用的第一个位置 
Buffer crear()
//通过将位置复位到0，并将界限设置到容量，使这个缓冲区为写出做好准备。返回this
Buffer filp()
//通过将界限 设置到 上一次写入的位置，并将position位置复位到0，使这个缓冲区做好读的操作，返回this。
Buffer rewind()
//通过将读写位置复位到0，并保持界限不变，使这个缓冲区为重新读入相同的值做好准备。
Buffer mark()
//将这个缓冲区的标记设置到读写位置，返回this
Buffer reset()
//将这个缓冲区的位置设置到标记，从而允许被标记的部分可以再次被读入或者写出
int remaining()
//返回剩余可读入或写出的值的数量，即界限与位置之间的差异
int position()
void position(int newVaue)
//返回或设置这个缓冲区的位置
int caapacity()
//返回这个缓冲区的容量
~~~

~~~java
				java.io.ByteBuffer extends Buffer 

byte get()
//从当然位置获取一个字节，并当前位置移动到 下一个字节
byte get(int index)
//从指定索引获得一个字节
ByteBuffer put(byte b)
ByteBuffer put(int index,byte b)
//向前位置推一个字节，并将当前位置移动到下一个字节。返回对这个缓冲区的引用。
ByteBuffer get(byte[] destingation)
ByteBuffer get(byte[] destingation,int offset,int length)
//用缓冲区中的字节 来填充 字节数组，或者字节数组的 (第二个方法) 某个区域，并将当前位置 向前移动读入的 字节 数个位置。如果缓冲区不够大，那么就不会读入任何字节，并抛出BufferUnderflow Exception。
ByteBuffer put(byte[] source)
ByteBuffer put(byte[] source,int offset,int length)
//将字节数组中的所有字节 或者给定区域的字节都推入缓冲区， 并将当前位置向前 移动 写出的 字节 数个位置。如果缓冲区不够大，那么就不会读入任何字节。并抛出 BufferUnderflowException。

Xxx getXxx()
Xxx getXxx(int index)
ByteBuffer putXxx()
ByteBuffer putXxx(int index,Xxx value)
//获得或放置一个二进制数，Xxx是Int,Long,Short,Char,Flaot或Double中的任意一个

ByteBuffer order(ByteOrder order)
ByteBuffer ordr()
//设置或获取字节顺序，order的值是ByteOrder类的常量 BIG_ENDINA 或 Little_Endina中的一个

static ByteBuffer allocate(int capacity)
//构建具有指定容量的缓冲区
static ByteBuffer wrap(byte[] values)
//构建具有指定容量的缓冲区，它该缓冲区是对给定数组的包装
static Buffer asCharBuffer()
//构建字符缓冲区，它是对这个缓冲区的包装，对该字符缓冲区的变更将在这个缓冲区中反映出来，但是该字符缓冲区有自己的位置，界限和标记

~~~

## 缓冲区数据结构

每个缓冲区具有:

- 一个容量(capacity)，它永远不能改变
- 一个读写位置(position)，下一个值将在次进行读写
- 一个界限(limit)，超过他进行读写是没有意义的
- 一个可选的标记，可以用于重复一个读入或写出操作

使用缓冲区，在进行读的时候 界限是在 上一次写入的位置，position到位置0，每次读取数据的时候往limit方法移到，知道到达limit停止。在进行写的时候界限在 capacity。

## 文件加锁

~~~java
				java.nio.channels.FileChannel 
FileLock lock()
//在整个文件上获得一个独占的锁，这个方法将阻塞知道获取到锁
FileLock tryLock()
//在整个文件上获得一个独占锁，或者在无法获取到锁的情况下返回null
FileLock lock(long position,long szie,boolean shard)
FileLock tryLock(long position,long size,boolean shard)
//在文件的一个区域上获得锁。第一个方法将阻塞到获得锁，而第二个方法 则将无法获得锁返回null
	//参数: position 开始的位置 size 区域的最大 shared ture为共享锁 false为独占锁

java.nio.channnels.FileLock
void close()
//释放掉这个锁
~~~



