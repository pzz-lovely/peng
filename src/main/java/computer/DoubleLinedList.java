package computer;


/**
 * @Auther lovely
 * @Create 2020-04-13 9:42
 * @Description todo
 */
public class DoubleLinedList<V> {

    private int capacity ;
    private Node head;
    private Node tail;
    private int size;

    public DoubleLinedList(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 往头部节点
     */
    public V addHeader(V v) {
        Node< V> newNode = new Node(0, v);
        size++;
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            this.head.prev = null;
            this.tail.next = null;
        }else{ //将新节点设置为头节点
            Node node = tail;
            while ((node.prev) != null) {
                node = node.prev;
            }
            if (head.next == null) {
                head = newNode;
                head.next = node;
                node.prev = head;
                return v;
            }
            //新节点的下一个节点 为头节点
            Node oldHeadNode = head;
            oldHeadNode.key++;
            node.prev = oldHeadNode;
            newNode.next = oldHeadNode;
            //将头节点的上一个节点设置为新节点
            oldHeadNode.prev = newNode;
            //替换节点  这是新节点为头节点，而后面继续引用着 后续节点
            this.head = newNode;
            this.head.prev = null;
        }
        return newNode.value;
    }


    /**
     * 从尾部添加 节点
     */
    public V addTail(V value) {
        Node< V> newNode = new Node<>(0, value);
        size++;
        if (this.tail == null) {

            this.tail = newNode;
            this.head = newNode;
            this.tail.next = null;
            this.head.prev = null;
        }else{
            Node node = head;
            while ((node.next) != null) {
                node = node.next;
            }
            if (tail.prev == null) {
                tail = newNode;
                tail.prev = node;
                node.next = tail;
                return value;
            }

            Node oldTailNode = tail;
            node.next = oldTailNode;
            oldTailNode.key++;
            newNode.prev = oldTailNode;
            this.tail = newNode;
            oldTailNode.next = tail;
            this.tail.next = null;
            tail.key = 0;
        }
        return newNode.value;
    }

    /**
     * 从尾部删除的功能
     */
    private void delTail(){
        if (this.tail == null) {
            return;
        }
        tail = tail.prev;
        tail.key = 0;
        while (tail.prev != null) {
            tail.prev.key--;
        }
    }

    /**
     * 从头部删除
     */
    private void delHead(){
        if (this.head == null) {
            return;
        }
        Node headNext = head.next;
        headNext.key = 0;
        head = headNext;
    }

    /**
     * 删除中间节点
     */
    private void delMiddleNode(V value) {
        Node node = head.next;
        while (node != null) {
            if (node.value.equals(value)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                break;
            }
            node = node.next;
        }
    }
    /**
     * 删除节点
     * @param value
     */
    public void remove(V value) {
        if(this.tail.value.equals(value)) {
            delTail();
            size--;
        }
        else if (this.head.value.equals(value)) {
            delHead();
            size--;
        }else{
            //中间节点，需要遍历
            delMiddleNode(value);
            size--;
        }
    }
    public Object[] toArray(){
        Object[] nodes = new Object[size];
        Node node = head;
        for (int i =0; i < size; i++) {
            nodes[i] = node;
            node = node.next;
        }
        return nodes;
    }



    private static class Node<V>{
        private Node prev;
        private Node next;
        private Integer key;
        private V value;

        public Node(Integer key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
