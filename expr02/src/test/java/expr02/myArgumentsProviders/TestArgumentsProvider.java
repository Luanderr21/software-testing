package expr02.myArgumentsProviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        List list1 = Arrays.asList(1, 2, 3);
        List list2 = Arrays.asList(2, 3, 4);
        return Stream.of(1, 2, 3).map(Arguments::of);
    }
}


