import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountOfElements {

    public <T> Map<T, Long> arrayMapping(T[] array) {
        return Arrays.stream(array)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }
}
