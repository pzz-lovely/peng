package background;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther lovely
 * @Create 2020-03-12 15:08
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 * 发布溢出
 */
public class MultiThreadsError3 {
    private Map<String,String> states;

    public MultiThreadsError3() {
        this.states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
        states.put("5", "周五");
    }

    //溢出
    public Map<String, String> getStates() {
        return states;
    }

    //升级
    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }


    public String getValue(String key) {
        return states.get(key);
    }

    public static void main(String[] args) {

    }




}
