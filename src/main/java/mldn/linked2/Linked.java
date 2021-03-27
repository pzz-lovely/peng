/*
package mldn.linked2;

public class Linked<E> {
    private class Node<E>{
        private E data;                         //内容
        private Node<E> next;                   //下一个节点的引用

        public Node(E data) {
            this.data = data;
        }
        public void addNode(Node<E> newNode){
            if(this.next == null){
                this.next = newNode;
            }else {
                this.next.addNode(newNode);
            }
        }

        public void toArrayNode(){
            Linked.this.returnData[foot++] = this.data;
            if(this.next != null){
                this.next.toArrayNode();
            }
        }

        */
/**
         * 根据指定索引获取指定接地那内容
         * @param index 指定的索引
         * @return 返回找到的内容
         *//*

       */
/* public E getNode(int index) {
            if (Linked.this.foot++ == index) {
                toArrayNode();
            }
        }*//*


    }

    */
/**********************************************************//*

    private Node root;                              //根节点
    private int count;                              //调用add()方法
    private int foot;                               //数组的脚标
    public void add(E data) {
        if(data == null)
            return;
        Node<E> node = new Node<>(data);
        if(root == null)
            root = node;
        else
            root.addNode(node);
        count++;
    }

    */
/**
     * 返回当前节点的个数
     * @return 节点的总数
     *//*

    public int size(){
        return count;
    }

    */
/**
     * 判断接待是否为空
     * @return this.count
     *//*

    public boolean isEmpty(){
        return count == 0;
    }

    public E[] toArray(){

        foot=  0 ;                                  //脚标清0
        returnData = new Object[count];             //
        root.toArrayNode();
        return (E[]) returnData;
    }

    public E get(int index){
        foot = 0;
        if(index > count){
            throw new NullPointerException("索引越界");
        }
        return (E) root.getNode(index);
    }
}
*/
