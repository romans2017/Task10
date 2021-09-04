import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasks {

    public static List<Integer> doubling(List<Integer> list) {
        return list.stream().parallel().map(item -> item * 2).filter(item -> item % 10 != 2).collect(Collectors.toList());
    }

    public static List<String> excludeZ(List<String> list) {
        return list.stream().parallel().filter(item -> !item.contains("z") && !item.contains("Z")).collect(Collectors.toList());
    }

    public static List<String> analyzeWordsFrequency() {
        Map<String, Integer> map = new HashMap<>();
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\resources\\text.txt"))) {
            bufferedReader
                    .lines()
                    .flatMap(item -> Stream.of(item.split("[\\s\\t]")))
                    .map(item -> item.toLowerCase(Locale.ROOT))
                    .filter(item -> !"".equals(item))
                    .forEach(item -> map.merge(item, 1, Integer::sum));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
        return map.entrySet()
                .stream()
                .parallel()
                .sorted((item1, item2) -> {
                    int res = item2.getValue() - item1.getValue();
                    if (res == 0) {
                        return item1.getKey().compareTo(item2.getKey());
                    } else {
                        return res;
                    }
                })
                .flatMap(item -> Stream.of(item.getKey()))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(doubling(List.of(1, 3, 4, 2, 12, 11)));
        System.out.println(excludeZ(List.of("asdsa", "aszsa", "asewrwsa", "asZsa")));
        System.out.println(analyzeWordsFrequency());
    }
}