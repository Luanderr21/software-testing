package expr01;

import com.ctgu.expr01.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    @DisplayName("边长错误测试")
    void error_classification() {
        assertEquals("错误边长", new Triangle(0, 1, 5).classify());
    }

    @Test
    @DisplayName("非等边三角形测试")
    void normal_classification() {
        assertEquals("非等边三角形", new Triangle(2, 3, 4).classify());
    }

    @Test
    @DisplayName("等边三角形测试")
    void equal_sides_classification() {
        assertEquals("等边三角形", new Triangle(1, 1, 1).classify());
    }
}
