package mldn.binaryTree;

import javax.xml.soap.Node;

/**
 * @Auther lovely
 * @Create 2020-03-31 19:59
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class BinaryTreeImpl<E> implements IBinaryTree<E> {
    //该内部类只为当前的外部类提供服务
    private class Node{
        //要存储的数据，全部进行强制性转换
        private Comparable<E> data;
        private Node left;  //左子树
        private Node right; //右子树
        private Node parent;//父节点
        //节点创建保存数据
        public Node(Comparable<E> data) {
            this.data = data;
        }


        public void toArrayNode(){  //实现数据的递归
            if (this.left != null) {    //此时有左子树
                this.left.toArrayNode();
            }
            BinaryTreeImpl.this.data[BinaryTreeImpl.this.foot++] = (E)this.data;   //
            if (this.right != null) {   //此时存在右结构
                this.right.toArrayNode();//递归调用
            }
        }

        public Node containsNode(E data) {
            if(this.data.compareTo(data) == 0){ //数据相同
                return this;    //返回当前节点
            }else{
                if(this.data.compareTo(data) < 0) { //当前的节点小于判断的数据
                    if(this.right != null){ //存在右节点
                        return this.right.containsNode(data);   //继续判断后续节点
                    }else{
                        return null;
                    }
                }else{
                    if(this.left != null)
                        return this.left.containsNode(data);
                    else
                        return null;
                }
            }
        }

    }
    //--------------------二叉树实现结构

    //数据结构都需要提供根节点
    private Node root;
    private int count = 0;
    private int foot;//数组的索引
    private Object[] data;//返回的对象数组


    @Override
    public void add(E data) {
        if(data == null)
            throw new NullPointerException("存储在二叉树结构中的数据不允许为null");
        if(!(data instanceof Comparable))
            throw new ClassCastException("保存数据对象的类没有实现java.lang.Comparable接口");
        //将数据保存在节点之中
        Node newNode = new Node((Comparable) data);
        if(this.root == null)
            this.root = newNode;    //保存节点
        else{                       //需要确定节点的位置
            Node currentNode = this.root;
            while (currentNode != newNode) {    //当前节点不是新节点，表示新节点未保存
                //比根节点大
                if (currentNode.data.compareTo(data) <= 0) {
                    if (currentNode.right != null) {
                        currentNode = currentNode.right;
                    }else{      //此时不存在有 右节点
                        currentNode.right = newNode;
                        newNode.parent = currentNode;
                        currentNode = newNode;  //结束循环
                    }
                }else{
                    if(currentNode.left != null)
                        currentNode = currentNode.left; //设置当前节点为左节点
                    else{
                        currentNode.left = newNode;//保存新节点
                        newNode.parent = currentNode;
                        currentNode = newNode;
                    }
                }
            }
        }
        this.count++;
    }


    @Override
    public int size() {
        return this.count;
    }

    @Override
    public Object[] toArray() {
        if(this.size() == 0)
            return null;
        this.data = new Object[this.size()];
        this.foot = 0;  //角标清0
        this.root.toArrayNode();
        return data;
    }

    @Override
    public boolean contains(E data) {
        if(data == null)
            throw new NullPointerException("存储在二叉树结构中的数据不允许为null");
        if(!(data instanceof Comparable))
            throw new ClassCastException("保存数据对象的类没有实现java.lang.Comparable接口");
        if(this.size() == 0)
            return false;
        return this.root.containsNode(data) != null;
    }

    @Override
    public void remove(E data) {
        if(data == null)
            throw new NullPointerException("存储在二叉树结构中的数据不允许为null");
        if(!(data instanceof Comparable))
            throw new ClassCastException("保存数据对象的类没有实现java.lang.Comparable接口");
        if (this.contains(data)) {  //删除的是否是根节点
            if (this.root.data.compareTo(data) == 0) {
                this.root = this.moveNode(data);//移动节点
            }else
                this.moveNode(data);
            count--;
        }
    }

    private Node moveNode(E data) {
        Node moveNode = null;//假设当前的节点 为要移动的子节点
        Node deleteNode = this.root.containsNode(data);
        if(deleteNode == null){ //不存在删除节点
            return  null;//没有要移动的节点
        }
        //情况1：要删除的节点没有任何的子节点存在
        if (deleteNode.left == null && deleteNode.right == null) {
            //在树的结构之中根节点不存在有父节点，所以刺死也是需要进行跟根节点的判断
            if (deleteNode.parent != null) {    //存在父节点的引用
                if(deleteNode.parent.data.compareTo(data) <= 0){
                    deleteNode.parent.right = null;
                }else{
                    deleteNode.parent.left = null;
                }
            }
            //处理的是根节点，则取消根节点的父节点
            deleteNode.parent = null;
        }
        //情况2：如果待删除节点只有一个子节点
        if(deleteNode.left != null & deleteNode.right==null || (deleteNode.left == null & deleteNode.right != null)){
            moveNode = null;    //要移动的节点，可能是左节点，也可能是右节点
            if (deleteNode.left != null) {
                moveNode = deleteNode.left;
            }else{
                moveNode = deleteNode.right;
            }
            //删除节点存在有父节点
            if (deleteNode.parent != null) {
                if(deleteNode.parent.data.compareTo(data) <= 0){    //由节点
                    deleteNode.parent.right = moveNode;
                }else{ //左节点
                    deleteNode.parent.left = moveNode;
                }
            }
            moveNode.parent = deleteNode.parent;
        }

        //情况3：待删除的有两个子节点，就需要确定右子树中最小节点
        if (deleteNode.left != null && deleteNode.right != null) {
            moveNode = deleteNode.right;    //要移动的节点设置为删除节点的右节点
            while (moveNode.left != null) { //找到右边的最左节点
                moveNode = moveNode.left; //找到最左节点
            }
            /*if (moveNode.right != null) {//已经移动到的节点存在有右节点
                //moveNode.parent.left = moveNode.right;//修改节点位置
                //moveNode.right.parent = moveNode.parent;//修改右节点的父节点
            }else{  //右节点不存在
                if (deleteNode.right != moveNode) {//删除节点的右节点不是移动节点
                    moveNode.parent.left = null;//删除移动节点对应的左节点
                }
            }*/
            moveNode.parent = deleteNode.parent;//修改移动节点的父节点
            moveNode.left = deleteNode.left;//修改节点的左节点
            if (deleteNode.right != moveNode) { //考虑到右节点的问题
                moveNode.right = deleteNode.right;//修改右节点的引用
            }
            if (deleteNode.parent != null) {//存在有删除节点
                if (deleteNode.parent.data.compareTo(data) <= 0) {
                    deleteNode.parent.right = moveNode;//移动子节点
                }else{
                    (deleteNode).left = moveNode;//移动子节点
                }

            }
        }
        return moveNode;    //返回移动的子节点
    }
}
