package http;

import java.math.BigInteger;

/**
 * @Auther lovely
 * @Create 2020-03-28 9:09
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Request {
    private BigInteger value;

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }
}
