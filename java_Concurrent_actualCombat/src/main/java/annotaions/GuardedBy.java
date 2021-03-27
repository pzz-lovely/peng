package annotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The field or method to which this annotation is applied can only be accessed
 * when holding a particular lock which my be a built-in(synchronization) lock
 * or may be an explicit java.util.concurrent.Lock
 * The argument determines which lock guards the annotated field or method :
 * <ul>
 *     <li><code>this</code>:The intrinsic lock of the object in whose class the field is defined定义字段所在类的对象的固有锁定</li>
 *     <li><code>class-name.this</code>: For inner classes,it may be necessary to disambiguate 'this' 对于内部类，可能有必要消除“ this”的歧义
 *     the <em>class-name.this</em>designation allows you to specify which 'this' reference is intended 名称允许您指定要使用的“ this”参考
 *     </li>
 *     <li><code>itself 本身</code> : For reference fields only; the object to which the field refers ：仅供参考；字段所引用的对象</li>
 *     <li><code>field-name</code>: The lock object is referenced by the (instance or static) field 锁对象由（实例或静态）字段引用
 *     by <em>class-name.field-name</em>
 *     </li>
 *     <li><code>class-name.class</code>: The Class object for the specified class should be used as the lock object 指定类的Class对象应用作锁定对象</li>
 * </ul>
 * 仅当持有特定锁（可以是内置（Synchronized）锁）或可能是显式java.util.concurrent.Lock时，才可以访问应用了此批注的字段或方法。*该参数确定哪个锁保护带注释的字段或方法：
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy {
    String value();
}
