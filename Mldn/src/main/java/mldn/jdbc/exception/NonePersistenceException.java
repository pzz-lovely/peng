package mldn.jdbc.exception;

/**
 * @Auther lovely
 * @Create 2020-03-30 21:36
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class NonePersistenceException extends RuntimeException {
    public NonePersistenceException() {
    }

    public NonePersistenceException(String message) {
        super(message);
    }
}
