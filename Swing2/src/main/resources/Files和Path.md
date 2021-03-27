# 操作文件

## path

Path表示的是一个目录名序列。路径的第一个`部件`可以是根部件。

1. Paths.get()方法接受一个或多个字符串，并将它们用默认文件系统的路径分隔符连接起来
2. path.resolve(q)将按照 如果q是绝对路径则结果就是q 否根据文件系统的规则 将p后面跟着q作为结果
3. resolveSibing()它通过解析指定的路径的父路径产生其兄弟路径

~~~java
	java.nio.file.Path  extends Comparable<Path>, Iterable<Path>, Watchable

Path resolve(Path other)
Path resolve(String ohter)
//如果other是绝对路径，那么就返回other；否则通过连接this和other获得的路径

Path resolveSibing(Path other)
Path resolveSibing(String other)
//如果other是绝对路径，那么就返回other,否则返回通过连接this的父路径和other的路径

Path relativize(Path other)
//返回用this进行解析，相对于other的相对路径。

Path normalize()
//移出诸如.和...等冗余的路径元素

Path toAbsoutePath()
//返回与该路径等价的绝对路径

Path getParent()
//返回父路径，或者在该路径没有父路径时，返回null

Path getFileName()
//返回该路径的最后一个部件，或者在该路径没有任何部件时，返回null

Path getRoot()
//返回该路径的根部件，或者在该路径没有任何根部件时，返回null

File toFile()
//从该路径中创建一个File对象

		java.nio.files.Paths
static Path get(String first,String...more)
//通过连接给定的字符串创建一个路径
~~~



Files类可以是普通文件操作变得快捷

~~~java
		java.nio.file.Files 

private Files()

static byte[] readAllBytes(Path path)
static List<String> readAllLines(Path path,Charset charset)
//读入文件的内容

/*读写文件*/
static Path write(Path path,bytep[] contents,OpenOption...options)
static Path write(Path path,Iterable<? extends CharSequence content,OpenOption options)
//将给定内容写出到文件中，并返回path

static InputStream newInputStream(Path path,OpenOption...options)
static OutputStream newOutputStream(Path path,Openoption...options)
static BufferedReader newBufferedReader(Path path,Charset charset)
static BufferedWriter newBufferedWriter(Path path,Charset charset,OpenOption...options)
//打开一个文件，用于读入和写出

/*创建Files*/
static Path createFile(Path path,FileAttribute<?>...attrs)
static Path createDirectory(Path path,FileAttributes<?>...atttrs)
static Path createDirectories(Path path,FileAttribute<?>...attrs)
//创建一个文件或目录的，createDirectories还会创建路径中所有的中间目录

static Path createTempFile(String prefix,String suffix,FileAttrbute<?>...attrs)
static Path createTempFile(Path parentDir,String prefix,String suffix,FileAttribute<?>...attrs)
static Path createTempDirectory(String prefix,FileAttribute<?>...attrs)
static Path createTempDirectory(Path parentDir,String prefix,FileAttrbute<?>...attrs)
//在合适临时文件的位置，或者在给定的父目录中，创建一个临时文件或目录。返回所创建的文件或目录的路径

~~~



- 这种类型的目的是通过如方法中使用[`newOutputStream`](../../../java/nio/file/Files.html#newOutputStream-java.nio.file.Path-java.nio.file.OpenOption...-)  ， [`newByteChannel`](../../../java/nio/file/Files.html#newByteChannel-java.nio.file.Path-java.util.Set-java.nio.file.attribute.FileAttribute...-)  ， [`FileChannel.open`](../../../java/nio/channels/FileChannel.html#open-java.nio.file.Path-java.util.Set-java.nio.file.attribute.FileAttribute...-)和[`AsynchronousFileChannel.open`](../../../java/nio/channels/AsynchronousFileChannel.html#open-java.nio.file.Path-java.util.Set-java.util.concurrent.ExecutorService-java.nio.file.attribute.FileAttribute...-)打开或创建文件时。
- StandardOpenOption  extends OpenOption

|       选项        |                     描述                     |
| :---------------: | :------------------------------------------: |
|       Read        |                用于读取而打开                |
|       Write       |                用于写入而打开                |
|      Append       |    如果用于写入而打开，那么在文件末尾追加    |
| Truncate_Existing |     如果用于写入而打开，那么移出已有内容     |
|    Create_New     | 创建新文件并且在文件已存在的情况下会创建失败 |
|      Create       |      自动在文件不存在的情况下创建新文件      |
|  Delete_on_close  |      当文件被关闭时，竟可能地删除该文件      |
|      Sparse       |    给文件系统一个提示，表示该文件是稀疏的    |



- 这种类型的对象可以与使用[`Files.copy(Path,Path,CopyOption...)`](../../../java/nio/file/Files.html#copy-java.nio.file.Path-java.nio.file.Path-java.nio.file.CopyOption...-)  ， [`Files.copy(InputStream,Path,CopyOption...)`](../../../java/nio/file/Files.html#copy-java.io.InputStream-java.nio.file.Path-java.nio.file.CopyOption...-)和[`Files.move(Path,Path,CopyOption...)`](../../../java/nio/file/Files.html#move-java.nio.file.Path-java.nio.file.Path-java.nio.file.CopyOption...-)方法来配置一个文件是如何复制或移动。 

- StandardCopyOption extends CopyOption

  |       选项       |           描述           |
  | :--------------: | :----------------------: |
  |   Atomic_Move    |     原子性地移动文件     |
  | Copy_Attributes  |      复制文件的属性      |
  | Replace_Existing | 如果目标已存在，则替换他 |



~~~java
			java.nio.file.Files

static Path copy(Path from,Path to,CopyOption...options)
static Path move(Path from,Path to ,CopyOption..options)
//将from复制或移动到给定位置，并返回to

static long copy(InputStream from,Path to ,CopyOption...options)
static long move(Path from,OutputStream to,CopyOption...options)
//从输入流复制到文件中，或者从文件复制到输出流中，返回复制的字节数

static void delete(Path path)
static boolean deleteIfExists(Path path)
//删除给定文件或空目录。第一个方法在文件或目录不存在情况下抛出异常，而第二个方法在这种情况下返回false
~~~



获取文件信息

~~~java
java.nio.files.Files
    
static boolean exists(Path path)
static boolean isHidden(Path path)
static boolean isReadable(Path path)
static boolean isWritable(Path path)
static boolean isExecutable(Path path)
static boolean isRegularFile(Path path)
static boolean isDirectory(Path path)
static boolean isSymolicLink(Path path)
//检查有路径指定的文件的指定的属性

static long size(Path path)
//获取文件按字节数 度量的尺寸
~~~


属性

~~~java
java.nio.file.attribute.BasicFileAttributes

FileTime creationTime()
FileTime lastAccessTime()
FileTime lastModifiedTime()
boolean isRegularFile()
boolean isDirectory()
boolean isSymbolicLink()
long size()
Object fileKey()
//获取所请求的属性
~~~

访问目录中的项

1. Files.list(Path path)会返回有一个可以读取目录中各个项的Stream<Path\>对象。目录是被惰性读取的。
2. list方法不会进入子目录，为了处理目录中所有的子目录，需要使用File.walk(Path path)方法
3. 可以通过Files.walk(Path path,FileVisiOption...option) 来限制树的深度

~~~java
			java.nio.file.Files

static DirectoryStream<Path> newDirectoryStream(Path path)
static DirectoryStream<Path> newDirectoryStream(Path path,String glob)
//获取给定目录中可以遍历的所有文件和目录的迭代器。第二个方法只能接受那些给定的glob模式匹配的项
static Path walkFileTree(Path start,FileVisitor<? super Path> visitor)
//遍历给定路径的所有子孙，并将访问器应用这些子孙之上
    
	java.nio.file.SimpleFileVisitor<T> implements FileVsitor<T>

static FileVisitResult visitFile(T path,BasicFileAttribute attrs)
//在访问文件 或 目录被调用，返回Continue、Skip_Subtree、Skip_Siblings和Terminate之一，默认实现是不做任何操作而继续访问
static FileVisitResult preVisitDirectory(T dir,BasicFielAttributes attrs)
static FileVisitResult postVisiDirectroy(T dir,BasicFileAttributes attrs)
//在访问目录之前和之后被调用，默认是做任何操作而继续访问
static FileVisiResult visitFileFailed(T path,IOException exc)
//如果在视图获取给定文件的信息是抛出异常，则该方法被调用。默认实现是重新抛出异常，这会导致访问操作以这个异常终止。如果你想自己访问，可覆盖这个方法。
~~~

| 模式  |                        描述                         |                     示例                      |
| :---: | :-------------------------------------------------: | :-------------------------------------------: |
|   *   |           匹配路径组成部分中0个或多个字符           |      *.java匹配当前目录中所有的java文件       |
|  **   |            匹配跨目录边界的0个或多个字符            |      **.java匹配在所有子目录中的java文件      |
|   ?   |                    匹配一个字符                     |     ????.java匹配所有的四个字符的java文件     |
| [...] | 匹配一个字符集合，可以使用连线符[0-9]和取反符[!0-9] |        Test[0-9A-Z].java匹配TextA.java        |
| {..}  |           匹配有逗号隔开的多个可选项之一            | *.{java,class}匹配所有的java文件和类class文件 |
|   \   |           转义上述任意模式的字符已经\字符           |      *\\**匹配所有文件名所有包含*的文件       |


### ZIP文件系统

Path类会在默认文件系统中查找路径。

~~~java
			java.nio.file.FileSystems
static FileSystem newFileSystem(Path path,ClassLoader loader)
//对所安装的文件系统提供者进行迭代，并且如果loader不为null，那么就还迭代给定的类加载器能够加载的文件系统，返回由第一个可以接受给定路径的文件系统提供者创建的文件系统。默认情况下，对于ZIP文件系统是有一个提供者的，它接受的名字以.zip或.jar结尾的文件

static Path getPath(String first,String...more)
//将给定的字符串连接起来创建一个路径。
~~~

