package io.textFile;

import entity.Book;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @Auther lovely
 * @Create 2020-03-12 22:33
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class TxetFileTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Book[] books = new Book[]{new Book("0.0", 10), new Book("0.1", 16), new Book("0.2", 12)};
        try (PrintWriter out = new PrintWriter("book.dat", "utf-8")) {

        }
    }

    public static void writeBook(PrintWriter out, Book book) {
        out.println(book.toString());
    }

}
