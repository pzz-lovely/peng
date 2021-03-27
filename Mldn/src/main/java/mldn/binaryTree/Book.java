package mldn.binaryTree;

/**
 * @Auther lovely
 * @Create 2020-03-31 20:41
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Book implements Comparable<Book> {

    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }


    @Override
    public int compareTo(Book o) {
        if (this.price > o.price) {
            return 1;
        } else if (this.price < o.price) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
