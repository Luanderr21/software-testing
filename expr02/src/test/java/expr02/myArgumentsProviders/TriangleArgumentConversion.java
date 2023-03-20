package expr02.myArgumentsProviders;

import com.ctgu.expr02.Triangle;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class TriangleArgumentConversion extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        String sourceString = (String)source;
        String[] res = sourceString.split(",");
        int a = Integer.valueOf(res[0]);
        int b = Integer.valueOf(res[1]);
        int c = Integer.valueOf(res[2]);
        return new Triangle(a, b, c);
    }
}
