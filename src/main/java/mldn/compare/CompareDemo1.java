package mldn.compare;

import java.util.Arrays;

public class CompareDemo1 {
    public static void main(String[] args) {
        Book books[] = new Book[]{
                new Book("java", 39),
                new Book("C#", 59),
                new Book("Python", 40)
        };
        Arrays.sort(books,/*CompareDemo1::compare*/(book1,book2)->{
            if (book1.getPrice() > book2.getPrice()) {
                return -1;
            }else if (book1.getPrice() < book2.getPrice()) {
                return 1;
            }
            return 0;
        });
        System.out.println(Arrays.toString(books));
    }

    public static int compare(Book book1,Book book2){
        if (book1.getPrice() > book2.getPrice()) {
            return -1;
        }else if (book1.getPrice() < book2.getPrice()) {
            return 1;
        }
        return 0;
    }
}
