package annotaions;

import java.lang.annotation.*;

/**
 * The class to which this annotation is applied  is immutable. This means that its state cannot be seen to change by callers,which implies that
 * <ul>
 *     <li>all public fields are final���й��������Ϊ���վ���</li>
 *     <li>all public final reference fields refer to other immutable objects,and ���й������������ֶζ������������ɱ���󣬲���</li>
 *     <li>constructors and method do not publish references to any internal state which is potentially mutable by the implementation ���캯���ͷ������ᷢ�����κ��ڲ�״̬�����ã����ڲ�״̬���ܻ���ʵ�ַ�ʽ����</li>
 * </ul>
 * Immutable objects may still have internal mutable state for purposes of performance
 * optimization;some state variables may be lazily computed, so long as they are computed
 * from immutable state and that callers cannot tell the difference
 * ���ɱ�����������Ŀ�Ŀ����Ծ����ڲ��ɱ�״̬�Ż���ĳЩ״̬�����������ӳټ���ģ�ֻҪ�����ǴӲ��ɱ�״̬����ģ����ҵ������޷��ֱ������
 * <p>
 *     Immutable objects are inherently thread-safe;they may passed between threads or published without synchronization
 * </p>
 * Ӧ�ô�ע������ǲ��ɱ�ġ�����ζ�ŵ������޷�������״̬���ģ�����ζ��
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Immutable {
}
