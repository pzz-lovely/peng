package regex;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String url = "http://www.aifou.cn/category/list/pve/1.html";
        String regex_title = "<span class=\"title\">.{1,50}</span>";
        String resource = Spider.getSource(url);
        String label = Spider.getLabel(resource, regex_title);
        String[] text = label.split("<span class=\"title\">");
        ArrayList<Product> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            text[i].replace("</span>","");
        }
    }
}
