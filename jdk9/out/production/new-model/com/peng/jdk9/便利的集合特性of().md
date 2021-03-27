* 快速创建只读集合，java8之前

~~~java
public class Jdk8ListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Lilei");
        list.add("HanMeimei");

        // 调用Collections中的方法，将list变为只读的
        List<String> newList = Collections.unmodifiableList(list);
        // 不能执行，否则会报错
        newList.add("Tim");
        // 遍历
        newList.forEach(System.out::println);

        // Set
        Set<Integer> sets = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));

        // Map
        Map<Object, Object> map = Collections.unmodifiableMap(new HashMap<>(){
            {
                put("Tom", 78);
                put("Jerry", 88);
                put("Tim", 68);
            }
        });
    }
}
~~~

jdk9

~~~java
public class Jdk9ListDemo {
    public static void main(String[] args) {
        List<Integer> list = Collections.unmodifiableList(List.of(1, 2, 3));

        Set<Integer> sets = Set.of(1, 2, 3);

        Map<String, Integer> maps = Map.of(
                "Tom", 23,
                "Jerry", 22,
                "Lilei", 12
        );

        Map<String, Integer> map = Map.ofEntries(Map.entry("Tom", 23), Map.entry("Jerry", 21));
    }
}
~~~