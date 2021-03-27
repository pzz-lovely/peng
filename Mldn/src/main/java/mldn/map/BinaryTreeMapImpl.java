package mldn.map;

/**
 * @Auther lovely
 * @Create 2020-04-01 15:42
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BinaryTreeMapImpl<K,V> implements IMap<K,V> {

    //如果要想进行树状结构的存储，必须要想办法把key和value进行一次包装处理
    private class Entry<K, V> implements Comparable<Entry<K, V>> {
        private K key;  //key所在的类必须实现Comparable 接口
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable)this.key).compareTo(o.key);
        }
    }
    //如果要想进行数据的存储肯定要有一个Node节点存在
    private class Node{
        private Entry<K,V> data;
        private Node left;
        private Node right;
        //节点必须要包裹Entry对象
        public Node(Entry<K, V> data) {
            this.data = data;
        }
        //递归方式 实现节点存储
        public V addNode(Node newNode) {
            if (this.data.compareTo(newNode.data) < 0) {    //新的内容大于根节点
                if (this.right == null) {   //没有右节点
                    this.right = newNode;
                }else{
                    return this.right.addNode(newNode);
                }
            } else if (this.data.compareTo(newNode.data) > 0) { //新的内容小于根节点
                if(this.left == null)
                    this.left = newNode;
                else
                    return this.left.addNode(newNode);
            }else if(this.data.compareTo(newNode.data) == 0){  //可以存在，
                V old = this.data.value;//获取旧的内容
                this.data.value = newNode.data.value;
                return old;
            }
            return null;
        }

        public V getNode(K key){
            if(this.data.key.equals(key))   //key相同
                return this.data.value;
            else{
                if (((Comparable) this.data.key).compareTo(key) <= 0) {
                    if(this.right != null)
                        return this.right.getNode(key);
                    else
                        return null;
                }else{
                    if(this.left != null)
                        return this.left.getNode(key);
                    else
                        return null;
                }
            }
        }

    }

    private Node root;//定义根节点
    private int count; //定义元素的个数

    @Override
    public V put(K key, V value) {
        if(key == null || value ==null)
            throw new NullPointerException("保存数据的key1或value不予许为空");
        if (!(key instanceof Comparable)) {
            throw new ClassCastException("作为Key的所在类必须实现java.lang.comparable接口");
        }
        Entry<K, V> entry = new Entry<>(key, value);
        //将数据包裹在节点中
        Node newNode = new Node(entry);
        this.count++;
        if (this.root == null) {
            this.root = newNode;
            return null;
        }else{
            return this.root.addNode(newNode);
        }
    }

    @Override
    public V get(K key) {
        if(key == null )
            throw new NullPointerException("保存数据的key不予许为空");
        if (!(key instanceof Comparable)) {
            throw new ClassCastException("作为Key的所在类必须实现java.lang.comparable接口");
        }
        return this.root.getNode(key);//基于递归处理
    }

    @Override
    public int size() {
        return this.count;
    }
}
