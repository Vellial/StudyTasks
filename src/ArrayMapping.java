import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayMapping {

    public <T, R> List<R> arrayMapping(T[] array, Function<T, R> functionObj) {
        return Arrays.stream(array)
                .map(functionObj)
                .collect(Collectors.toList());

    }
}
