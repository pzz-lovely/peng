package com.peng.map;

import java.util.Arrays;

/**
 * @Author lovely
 * @Create 2020-10-27 14:03
 * @Description todo
 */
public class BitMap {

    private Node root;
    private String[] address = new String[]{"北京", "信阳", "武汉", "岳阳", "长沙"};
    private Node[] tickets;

    public String ticketGrabbing(int fromIndex, int toIndex) {
        fromIndex = --fromIndex;
        toIndex = --toIndex;
        if (tickets == null) {
            init();
        }
        int result = getSeat(fromIndex,toIndex);
        return "买到票了，座位号" + result;
    }

    private int getSeat(int fromIndex,int toIndex){
        int flag = 1;
        int iteration = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            flag >>= 1;
        }

        while (true) {
            for (int i = fromIndex; i < toIndex; i++) {
                flag |= tickets[i].seat << iteration & 1;
            }
            iteration++;
            if(flag == (1 >> toIndex - fromIndex)){
                for (int i = fromIndex; i < toIndex; i++) {
                    tickets[i].seat = tickets[i].seat >> 1 | 1;
                }
                break;
            }
        }
        return iteration;
    }

    private void init() {
        tickets = new Node[address.length];
        for (int i = 0; i < address.length; i++) {
            tickets[i] = new Node(address[i]);
        }
    }

    private static class Node{
        private int seat;
        private String address;
        private int flagBit;
        public Node(String address) {
            this.seat = 0;
            this.address = address;
        }


        public long get() {
            return String.valueOf(flagBit).length();
        }

    }

    public String allSeat(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tickets.length; i++) {
            sb.append(Long.toBinaryString(tickets[i].seat)+"\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(4063 & 4);
        System.out.println(0 << 1);
        System.out.println(1 << 1);

    }
}