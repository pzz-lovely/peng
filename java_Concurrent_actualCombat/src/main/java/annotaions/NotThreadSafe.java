package annotaions;

import java.lang.annotation.*;

/**
 * The class to which annotation is applied is not thread-safe.
 * This annotation primarily exists for clarifying the non-thread-safety of a class
 * that might otherwise be assumed to be thread-safe,despite the fact that it is a bad
 * idea to assume a class is thread-safe without good reason
 * Ӧ��ע�͵��಻���̰߳�ȫ�ġ� ��ע����Ҫ���ڲ�����ķ��̰߳�ȫ�Է�����ܱ���Ϊ���̰߳�ȫ�ģ�������ʵ�ǲ��õļٶ�����û�г�����ɵ��̰߳�ȫ���뷨
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotThreadSafe {
}
