package com.peng.jdk9.newhttp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author lovely
 * @create 2021-03-11 12:12
 * @description
 */
public class NewHttpDemo {
    public static void main(String[] args) {
        try{
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest req = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();

            HttpResponse<String> response = null;
            response = client.send(req, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.version().name());
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}