package tomcat.server.peng.util;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

//ģ����������������������
public class GetParm {
    private Map<String, String> map = new HashMap<>();
    public GetParm(){}

    public Map<String, String> getParm(String property) {
        String[] split3 = property.split("&");
        for (int i = 0; i < split3.length; i++) {
            String[] split4 = split3[i].split("=");
            map.put(split4[0], URLDecoder.decode(split4[1]));
        }
        System.out.println("����Ĳ��� "+map);
        return map;
    }
}
