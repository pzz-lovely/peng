package chapter2;

import http.Request;
import http.Response;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @Auther lovely
 * @Create 2020-03-28 9:08
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description 无状态 并没有人修改 状态里面的值
 */
public class Stateless {
    private ArrayList<BigInteger> list = new ArrayList<>();
    public void service(Request request, Response response){
        BigInteger value = extractFromRequest(request);
        BigInteger[] values = factor(value);
        encodeIntoResponse(response,values);
    }

    private BigInteger[] factor(BigInteger value) {
        list.add(value);
        System.out.println(value);
        return (BigInteger[]) list.toArray();
    }

    public BigInteger extractFromRequest(Request request) {
        return request.getValue();
    }

    public void encodeIntoResponse(Response response, BigInteger[] value) {
        response.send(value);
    }
}


