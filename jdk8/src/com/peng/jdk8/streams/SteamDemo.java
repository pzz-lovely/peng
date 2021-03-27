package com.peng.jdk8.streams;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lovely
 * @create 2021-03-11 8:49
 * @description
 */
public class SteamDemo {

    static List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        init();
        forEachStream();

    }


    public static void init(){
        List<String> imeis = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            List<String> singerVehicleDevices = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                String imei = random.nextInt(15) + "";
                singerVehicleDevices.add(imei);
            }
            imeis.add(StringUtils.join(singerVehicleDevices, ","));
        }
        vehicles.add(new Vehicle("KPTSOA1K67P081452","17620411498","9420",1,4.5,imeis.get(0)));
        vehicles.add(new Vehicle("KPTCOB1K18P057071","15073030945","张玲",2,1.4,imeis.get(1)));
        vehicles.add(new Vehicle("KPTS0A1K87P080237","19645871598","sanri1993",1,3.0,imeis.get(2)));
        vehicles.add(new Vehicle("KNAJC526975740490","15879146974","李种",1,3.9,imeis.get(3)));
        vehicles.add(new Vehicle("KNAJC521395884849","13520184976","袁绍",2,4.9,imeis.get(4)));
    }


    public static void forEachStream(){
        // Consumer<T> T为输入位，没有返回返回值
        // forEach遍历Collection数据
        vehicles.forEach(System.out::println);
    }

    public static void forEachMapStream() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "3");
        map.put("d", "2");

        // BiConsumer<T,U> T,U都作为输入值，没有返回值
        // forEach遍历Collection数据
        map.forEach(System.out::printf);
    }

    // 中间操作
    public static void filterStream(){
        // filter数据过滤
        // Predicate<T> T为输入值，返回boolean值
        // 去掉评分为 3 分以下的车
        List<Vehicle> collect = vehicles.stream().filter(
                vehicle -> vehicle.getScore() >= 3
        ).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void mapStream(){
        // map对象映射
        // Function<T,R> T为输入值，R为输出值
        // 取出所有车架号列表
        List<String> vins = vehicles.stream().map(Vehicle::getVin).collect(Collectors.toList());
        System.out.println(vins);
    }

    public static void groupByStream(){
        // Collector<? super T, A, R>  T为输入值，R为返回值，A为中间累计的值

        // 按照公司 id 进行分组
        Map<Integer, List<Vehicle>> companyVehicle = vehicles.stream().collect(Collectors.groupingBy(Vehicle::getCompanyId));

        // 按照公司分组求司机打分和
        Map<Integer, Double> collect = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getCompanyId, Collectors.summingDouble(Vehicle::getScore)));
    }

    public static void sortStream(){
        // Comparator<T> T为数值，返回int类型
        // 单列排序
        vehicles.sort((v1,v2) -> v2.getScore().compareTo(v1.getScore()));
        System.out.println(vehicles);

        // 或使用 Comparator 类来构建比较器，流处理不会改变原列表，需要接收返回值才能得到预期结果
        List<Vehicle> collect = vehicles.stream().sorted(Comparator.comparing(Vehicle::getScore).reversed()).collect(Collectors.toList());
        System.out.println(collect);

        // 多列排序，score降序，companyId 升序排列
        List<Vehicle> vehicleList =
                vehicles.stream().sorted(Comparator.comparing(Vehicle::getScore).reversed().thenComparing(Comparator.comparing(Vehicle::getCompanyId))).collect(Collectors.toList());
        System.out.println(vehicleList);
    }

    public static void flatMap(){
        // 查出所有车绑定的所有设备
        // flatMap 和适合 List<List> 或 List<Object[]>这种结构，可以当成一个列表来处理，像上面的设备猎豹，在数据库中存储的结构就是以逗号分隔的数据，而车辆列表又是一个列表数据
        List<String> collect = vehicles.stream().map(vehicle -> {
            String deviceNos = vehicle.getDeviceNos();
            return deviceNos.split(",");
        }).flatMap(Arrays::stream).collect(Collectors.toList());
    }

    public static void reduceStream(){
        // BinaryOperator<T> T为输入值，返回值也是T
        // 对所有司机的总分求和
        Double reduce = vehicles.stream().parallel().map(Vehicle::getScore).reduce(0d, Double::sum);

    }
}