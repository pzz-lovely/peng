package mldn.compare;

/**
 * @Auther lovely
 * @Create 2020-03-24 21:38
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Book implements Comparable<Book>{
    private String title;
    private double price;

    public Book() {
    }

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return this.price > o.price ? -1 :1;
    }
}
