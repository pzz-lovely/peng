package runtimeAnnotations.myAnnotationFactory;

/**
 * @Auther lovely
 * @Create 2020-03-16 9:30
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public interface IChannel extends AutoCloseable{
    boolean build();    //是否已经构建
}
