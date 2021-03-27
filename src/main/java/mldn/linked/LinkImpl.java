package mldn.linked;

public class LinkImpl<E>implements ILink<E> {
    private class Node<E> {
        private E data;     //节点保存数据
        private Node<E> next;   //保存节点引用

        public Node(E data) {
            this.data = data;
        }

        /**
         * 保存新创建的节点，保存的依据是判断当前节点的next属性释放为空s
         * @param newNode 要保存的新节点
         */
        public void addNode(Node<E> newNode) {
            if (this.next == null) {
                this.next = newNode;
            }else {
                this.next.addNode(newNode);
            }
        }

        /**
         * 将链表中的全部元素保存到对象数组中
         */
        public void toArrayNode(){
            //将当前节点的数据取出保存到returnData数组中，同时进行索引自增
            LinkImpl.this.returnData[LinkImpl.this.foot++] = this.data;
            if (this.next != null) {
                this.next.toArrayNode();            //递归调用
            }
        }


    }

    /*********************************************/
    private Node<E> root;               //根节点
    private int count;                  //用于进行元素个数的统计
    private int foot;                   //数组操作脚标
    private Object[] returnData;        //返回数据保存
    @Override
    public void add(E e) {
        if (e == null) {
            return;
        }
        Node<E> newNode = new Node<E>(e);         //创建一个新的节点
        if (this.root == null) {
            this.root = newNode;                  //第一个节点为根节点
        }else
            this.root.addNode(newNode);           //由Node类保存新接地那
        this.count++;                             //保存元素个数自增
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        if(count == 0){
            return false;
        }
        return true;
    }

    @Override
    public E[] toArray() {
        if (this.isEmpty()) {
            throw new NullPointerException("集合内容为空");
        }
        this.foot = 0;                  //脚标清0
        this.returnData = new Object[this.count];
        this.root.toArrayNode();        //利用Node类进行递归
        return (E[]) this.returnData;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E data) {

    }

    @Override
    public boolean contains(E data) {
        return false;
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public void clean() {

    }

}
