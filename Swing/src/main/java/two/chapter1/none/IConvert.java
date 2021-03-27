package two.chapter1.none;

/**
 * @Auther lovely
 * @Create 2020-02-28 16:07
 * @PACKAGE_NAME
 * @Description
 */
@FunctionalInterface
public interface IConvert<F, T> {
    T convert(F from);
}
