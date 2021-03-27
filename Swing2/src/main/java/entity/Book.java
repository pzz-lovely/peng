package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther lovely
 * @Create 2020-03-10 22:06
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private double price;
}
