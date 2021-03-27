package chapter2;

import http.Request;
import http.Response;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther lovely
 * @Create 2020-03-28 9:17
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Chapter2Test {
    public static void main(String[] args) {
        System.out.println("中文");
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.submit(() -> test1());
        }
        service.shutdown();
    }

    private static void test1() {
        Stateless stateless = new Stateless();
        Request request = new Request();
        request.setValue(new BigInteger("20000"));
        stateless.service(request,new Response());

    }
}
