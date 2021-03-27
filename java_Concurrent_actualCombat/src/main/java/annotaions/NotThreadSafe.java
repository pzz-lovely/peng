package annotaions;

import java.lang.annotation.*;

/**
 * The class to which annotation is applied is not thread-safe.
 * This annotation primarily exists for clarifying the non-thread-safety of a class
 * that might otherwise be assumed to be thread-safe,despite the fact that it is a bad
 * idea to assume a class is thread-safe without good reason
 * 应用注释的类不是线程安全的。 该注释主要用于阐明类的非线程安全性否则可能被认为是线程安全的，尽管事实是不好的假定类是没有充分理由的线程安全的想法
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
