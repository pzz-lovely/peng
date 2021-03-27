package annotaions;

import java.lang.annotation.*;

/**
 * The class to which this annotation is applied  is immutable. This means that its state cannot be seen to change by callers,which implies that
 * <ul>
 *     <li>all public fields are final所有公共领域均为最终决定</li>
 *     <li>all public final reference fields refer to other immutable objects,and 所有公共最终引用字段都引用其他不可变对象，并且</li>
 *     <li>constructors and method do not publish references to any internal state which is potentially mutable by the implementation 构造函数和方法不会发布对任何内部状态的引用，该内部状态可能会因实现方式而变</li>
 * </ul>
 * Immutable objects may still have internal mutable state for purposes of performance
 * optimization;some state variables may be lazily computed, so long as they are computed
 * from immutable state and that callers cannot tell the difference
 * 不可变对象出于性能目的可能仍具有内部可变状态优化；某些状态变量可能是延迟计算的，只要它们是从不可变状态计算的，并且调用者无法分辨出差异
 * <p>
 *     Immutable objects are inherently thread-safe;they may passed between threads or published without synchronization
 * </p>
 * 应用此注解的类是不可变的。这意味着调用者无法看到其状态更改，这意味着
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Immutable {
}
