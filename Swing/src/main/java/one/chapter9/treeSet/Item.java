package one.chapter9.treeSet;

import java.util.Objects;

/**
 * @Auther lovely
 * @Create 2020-02-20 10:23
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description An item with a description and part number
 */
public class Item implements Comparable<Item> {
    private String description;
    private int partNumber; //部分数量


    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        Item other = (Item) o;
        return Objects.equals(description, other.description) && partNumber == other.partNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), partNumber);
    }


    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(partNumber, o.partNumber);
        return diff != 0 ? diff : description.compareTo(o.description);
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", partNumber=" + partNumber +
                '}';
    }
}
