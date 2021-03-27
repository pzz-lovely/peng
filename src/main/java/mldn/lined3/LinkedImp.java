package mldn.lined3;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

public class LinkedImp<E>  implements Linked<E>{
    private class Node<E>{
        private Node<E> next;
        private E data;

        public Node(E data) {
            this.data = data;
        }

        /**
         *
         * @param newNode
         */
        public void add(Node<E> newNode) {
            if (this.next == null) {
                this.next = newNode;
            }else {
                this.next.add(newNode);
            }
        }

        public void toArrayNode(){
            LinkedImp.this.returnData[LinkedImp.this.foot++] = this.data;
            if (this.next != null) {
                this.next.toArrayNode();
            }
        }

        public void setNode(int index,E data){
            if (LinkedImp.this.foot++ == index) {
                this.data = data;
            }else {
                this.next.setNode(index, data);
            }
        }

        public E getNode(int index) {
            if (LinkedImp.this.foot++ == index) {
                return this.data;
            }else {
                return this.next.getNode(index);
            }
        }

        public boolean containsNode(E data) {
            if (this.data.equals(data)) {
                return true;
            }else {
                if (this.next == null) {
                    return false;
                }else {
                    return this.next.containsNode(data);
                }
            }
        }

        public void removeNode(Node previous,E data) {
            if (this.data.equals(data)) {
                previous.next = this.next;
            }else {
                if (this.next != null) {
                    this.next.removeNode(this, data);
                }
            }
        }
    }

    private Node<E> root;   //根节点
    private int count;       //元素个数
    private int foot;       //脚标
    private Object[] returnData;    //保存元素的个数

    @Override
    public void add(E data) {
        if (data == null) {
            throw new NullPointerException("数据为null");
        }
        Node<E> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        }else {
            root.add(newNode); ;
        }
        this.count++;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0 ;
    }

    @Override
    public Object[] toArray() {
        if (isEmpty()) {
            throw new NullPointerException("里面没有数据");
        }
        this.foot = 0;
        this.returnData = new Object[this.count];
        this.root.toArrayNode();
        return returnData;
    }

    @Override
    public void get(int index) {
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException("索引不正确");
        }
        foot = 0 ;
        this.root.getNode(index);
    }

    @Override
    public void set(int index, E data) {
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException("索引不正确");
        }
        foot = 0;
        this.root.setNode(index, data);
    }


    @Override
    public boolean containsData(E data) {
        if (data == null) {
            return false;
        }
        return this.root.containsNode(data);
    }

    @Override
    public void remove(E data) {
        if (data == null) {
            throw new NullPointerException("数据为null");
        }
        if (this.root.data.equals(data)) {
            this.root = this.root.next;
        }else {
            this.root.removeNode(this.root, data);
        }
        this.count--;
    }

    public void clear(){
        this.root = null;
        this.count = 0;
    }
}
