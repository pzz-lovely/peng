package executor.future;

/**
 * @Auther lovely
 * @Create 2020-08-19 14:34
 * @Description todo
 */
public interface Future<T> {
    public T get() throws InterruptedException;
}
