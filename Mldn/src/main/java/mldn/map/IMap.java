package mldn.map;

/**
 * @Auther lovely
 * @Create 2020-04-01 15:37
 * @PACKAGE_NAME
 * @Description 实现数结构的数据查询  定义一个根据树状结构存储的接口，同时实现数据的保存和查询
 */
public interface IMap<K, V> {
    /**
     * 向Map集合之中保存相应的数据内 容
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    V get(K key);

    int size();
}
