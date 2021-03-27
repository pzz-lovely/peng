package mldn.linked.demo;

import mldn.linked.ILink;
import mldn.linked.LinkImpl;

class PrintNode{
    public static void printNode(){}
}

public class LinkDemo {



    public static void main(String[] args) {
        ILink<String> link = new LinkImpl<>();
        link.add("0.0");
        link.add("0.1");
        link.add("0.2");
        link.add("0.3");
        Object result[] = link.toArray();
        for (Object obj : result) {
            String str = (String)obj;
            System.out.println(str+",");
        }
    }
}
