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
 *     <li><code>this</code>:The intrinsic lock of the object in whose class the field is defined�����ֶ�������Ķ���Ĺ�������</li>
 *     <li><code>class-name.this</code>: For inner classes,it may be necessary to disambiguate 'this' �����ڲ��࣬�����б�Ҫ������ this��������
 *     the <em>class-name.this</em>designation allows you to specify which 'this' reference is intended ����������ָ��Ҫʹ�õġ� this���ο�
 *     </li>
 *     <li><code>itself ����</code> : For reference fields only; the object to which the field refers �������ο����ֶ������õĶ���</li>
 *     <li><code>field-name</code>: The lock object is referenced by the (instance or static) field �������ɣ�ʵ����̬���ֶ�����
 *     by <em>class-name.field-name</em>
 *     </li>
 *     <li><code>class-name.class</code>: The Class object for the specified class should be used as the lock object ָ�����Class����Ӧ������������</li>
 * </ul>
 * ���������ض��������������ã�Synchronized���������������ʽjava.util.concurrent.Lockʱ���ſ��Է���Ӧ���˴���ע���ֶλ򷽷���*�ò���ȷ���ĸ���������ע�͵��ֶλ򷽷���
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GuardedBy {
    String value();
}
