Streams操作分为中间操作和晚间操作，中间操作会返回一个新的Stream，只是要把做的操作记录起来而已，并不会真的执行，晚间操作才会真的遍历列表并执行所有操作

Stream的另一个价值就是支持并行处理 paraller 方法。

Stream API 简化了集合的操作，并扩展了集合的分组，求合，mapReduce，flatMap，排序等功能，下面列出项目中经常用到的功能，会以使用频率排序。

测试对象：

~~~java
public class Vehicle {
    // 车架号
    private String vin;
    
    // 车主手机号
    private String phone;
    
    // 车主姓名
    private String name;
    
    // 所属车租车公司
    private Integer companyId;
    
    // 个人评分
    private Double score;
    
    // 安装的设备列表，使用逗号分隔
    private String deviceNos;
}
~~~

1. 准备车辆数据

~~~java
static List<Vehicle> vehicles new ArrayList<>()

public void init() {
        List<String> imeis = new ArrayList<>();
    for (int i = 0; i <5 ; i++) {
        List<String> singleVehicleDevices = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            String imei = RandomStringUtils.randomAlphanumeric(15);
            singleVehicleDevices.add(imei);
        }
        imeis.add(StringUtils.join(singleVehicleDevices,','));
    }
    vehicles.add(new Vehicle("KPTSOA1K67P081452","17620411498","9420",1,4.5,imeis.get(0)));
    vehicles.add(new Vehicle("KPTCOB1K18P057071","15073030945","张玲",2,1.4,imeis.get(1)));
    vehicles.add(new Vehicle("KPTS0A1K87P080237","19645871598","sanri1993",1,3.0,imeis.get(2)));
    vehicles.add(new Vehicle("KNAJC526975740490","15879146974","李种",1,3.9,imeis.get(3)));
    vehicles.add(new Vehicle("KNAJC521395884849","13520184976","袁绍",2,4.9,imeis.get(4)));
}
~~~
