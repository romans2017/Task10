import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTasks {

    public static List<Integer> doubling(List<Integer> list) {
        return list.stream().parallel().map(item -> item * 2).filter(item -> item % 10 != 2).collect(Collectors.toList());
    }

    public static List<String> excludeZ(List<String> list) {
        return list.stream().parallel().filter(item->!item.contains("z")&&!item.contains("Z")).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(doubling(List.of(1,3,4,2,12,11)));
        System.out.println(excludeZ(List.of("asdsa", "aszsa", "asewrwsa", "asZsa")));
    }
}