package demo;

import java.text.SimpleDateFormat;

public class Demo {
    public static final ThreadLocal<SimpleDateFormat> dataFormat = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));
}
