# zip

~~~.java
java.util.zip.ZipInputStream extends InflaterInputStream implements ZipConstants

ZipInputStream(InputStream in)
//创建一个ZipInputStream，使得我们可以从给定的InputStream想其中填充数据

ZipEntry getNextEntry()
//为下一项返回ZipEntry对象，或者没有在更多的项中返回null

void closeEntry()
关闭这个ZIP文件中当前打开的项 。之后可以通过使用getNextEntry()读入下一项
~~~

~~~java
java.util.ZipOutputStream  extends DeflaterOutputStream implements ZipConstants
    
ZipOutputStream(OutputStream out)
//创建一个将压缩数据写出到指定的OutputStream的ZipOutputStream

void putNextEntry(ZipEntry ze)
//将给定的ZipEntry中的信息写出到输出流，并定位用于写出数据的流，然后这些数据可以通过write()写出到这个输出流中
    
void closeEntry()
//关闭这个ZIP打开的项，使用oytNextEntry()方法可以开始下一项
    
void setLevel(int level)
//设置后续的各个DEFLATED想的默认压缩级别，这里的默认值是 Deflater.DEFAULT)COMPRESSION。如果级别无效则抛出IllegalArgumnetException
    //参数: level 压缩级别从0(No_Compression) 到 9(Best_Compression)

void setMethod(int method)
//设置用于这个ZipOutputStream的默认压缩方法，这个压缩方法会作用于所有没有指定的压缩方法的项上
    //参数: method 压缩方法 deflated 或 stored
~~~

~~~java
java.util.zip.ZipEntry implements ZipConstants, Cloneable
    
ZipEntry(String name)
//用给定名字构建一个ZIP项

long getCrc()
//返回用于这个ZipEntry的CRC32的校验和的值 

String getName()
//返回这一个项的名称

long getSize()
//返回这一项为压缩的尺寸，或者在未压缩的尺寸不可知的情况下返回-1

boolean isDirectory()
//当这一项是目录返回true

void setMethod(int method)
//用于这一项的压缩方法 必须是 deflated 或 stroed 
    
void setSize(long size)
//设置这一项的尺寸，只有在压缩方法是Stroed时才是必需的
	//参数 size 这一项未压缩的尺寸

void setCrc(long crc)
//给这一项设置Crc32的校验和，这个校验和是使用Crc32类计算的。只有在压缩方法是Stroed时才是必需的。
    //参数: crc 这一项的校验和
~~~

~~~java
java.util.zip.ZipFile implements ZipConstants, Closeable
 
ZipFile(String name)
ZipFile(File file)
//创建一个ZipFile,用于从给定的字符串或File对象中读入数据
    
Enumeration entries ()
//返回一个Enumeration对象，它枚举了描述这个ZipFile中各个项的ZipEntry对象

ZipEntry getEntry(String name)
//返回给定名字所对应的想，或者在没有对应项的时候返回null
    //参数 name 项名

InputStream getInputStream(ZipEntry ze)
//返回用于给定项InputStream。
    //参数 ze 这个ZIP文件中的一个ZipEntry

String getName()
//返回这个ZIP文件的路径
~~~









































