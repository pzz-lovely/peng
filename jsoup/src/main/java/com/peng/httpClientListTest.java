package com.peng;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class httpClientListTest {
    public void httpClientList(String url) {
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity, "utf-8");
                if (body != null) {
                    /*
                    替换掉换行符，制表符，回车符，去掉这些符号，泽政写起来更简单一些
                     */
                    Pattern p = Pattern.compile("\t|\r|\n");
                    Matcher m = p.matcher(body);
                    body = m.replaceAll("");
                    /*<div>><a href="https://voice.hup.com/nba/2485167.html"  target="_blank">与球迷亲切互动！凯尔特人官方晒球队开放训练日照片</a>                                   </div>*/
                    Pattern pattern = Pattern.compile("<div class=\\\"list-hd\\\">\\\\s* <h4>\\\\s* <a href=\\\"(.*?)\\\"\\\\s* target=\\\"_blank\\\">(.*?)</a>\\\\s* </h4>\\\\s* </div>");
                    Matcher matcher = pattern.matcher(body);
                    //匹配出所有符合正则表达式的数据
                    while (matcher.find()) {
                        System.out.println("详情页连接:"+matcher.group(1)+"详情页标题:"+matcher.group(2));
                    }
                }else {
                    System.out.println("处理失败，获取正文内容为空");
                }
            }else{
                System.out.println("处理失败，返回状态码" + response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "https://voice.hupu.com/nba";
        httpClientListTest http = new httpClientListTest();
        http.httpClientList(url);

    }
}
