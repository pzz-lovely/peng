package regex;



import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {




    /**
     * ��ȡ��ҳԴ����
     * @param url
     * @return
     */
    public static String getSource(String url) {
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        try{
            URL realUrl = new URL(url);
            //�򿪺�url֮�������
            URLConnection conn = realUrl.openConnection();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            if (reader != null) {
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    public static String getLabel(String resource, String regex) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(resource);
        while (matcher.find()) {
            sb.append(matcher.group(0));
        }
        return sb.toString();
    }

    public static List<String> getText(String resource, String regex) {
        List<String> quesUrl = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resource);
        while (matcher.find()) {
            quesUrl.add(matcher.group(1));
        }
        return quesUrl;
    }
}
