package expr02;

import com.ctgu.expr02.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TriangleTest {
    @ParameterizedTest
    @DisplayName("编译失败用例测试1")
    @CsvSource({"10.5, 10.5, 10.5, 编译失败"})
    void failedTest1(int a, int b, int c, String expectancy) {
        assertEquals(new Triangle(a, b, c).classify(), expectancy);
    }

    @ParameterizedTest
    @DisplayName("编译失败用例测试2")
    @CsvSource("10, 10, 编译失败")
    void failedTest2(int a, int b, int c, String expectancy) {
        assertEquals(new Triangle(a, b, c).classify(), expectancy);
    }

    @ParameterizedTest
    @DisplayName("一般边界值测试")
    @CsvSource({"1, 50, 50, 等腰三角形" ,
            "2, 50, 50, 等腰三角形" ,
            "99, 50, 50, 等腰三角形" ,
            "100, 50, 50, 非三角形" ,
            "50, 1, 50, 等腰三角形" ,
            "50, 2, 50, 等腰三角形" ,
            "50, 99, 50, 等腰三角形" ,
            "50, 100, 50, 非三角形" ,
            "50, 50, 1, 等腰三角形" ,
            "50, 50, 2, 等腰三角形" ,
            "50, 50, 99, 等腰三角形" ,
            "50, 50, 100, 非三角形" ,
            "10, 10, 50, 非三角形" ,
            "10, 50, 10, 非三角形" ,
            "50, 10, 10, 非三角形" ,
            "50, 50, 50, 等边三角形"})
    void normalTest(int a, int b, int c, String expectancy) {
        System.out.println(a+" "+b+" "+c+" "+expectancy);
        assertEquals(new Triangle(a, b, c).classify(), expectancy);
    }

    @ParameterizedTest
    @DisplayName("健壮边界值测试")
    @CsvSource({"0, 50, 50, 错误边长" ,
            "1, 50, 50, 等腰三角形" ,
            "2, 50, 50, 等腰三角形" ,
            "99, 50, 50, 等腰三角形" ,
            "100, 50, 50, 非三角形" ,
            "101, 50, 50, 错误边长" ,
            "50, 0, 50, 错误边长" ,
            "50, 1, 50, 等腰三角形" ,
            "50, 2, 50, 等腰三角形" ,
            "50, 99, 50, 等腰三角形" ,
            "50, 100, 50, 非三角形" ,
            "50, 101, 50, 错误边长" ,
            "50, 50, 0, 错误边长" ,
            "50, 50, 1, 等腰三角形" ,
            "50, 50, 2, 等腰三角形" ,
            "50, 50, 99, 等腰三角形" ,
            "50, 50, 100, 非三角形" ,
            "50, 50, 101, 错误边长" ,
            "10, 10, 50, 非三角形" ,
            "10, 50, 10, 非三角形" ,
            "50, 10, 10, 非三角形" ,
            "50, 50, 50, 等边三角形"})
    void strongTest(int a, int b, int c, String expectancy) {
        System.out.println(a+" "+b+" "+c+" "+expectancy);
        assertEquals(new Triangle(a, b, c).classify(), expectancy);
    }

    @ParameterizedTest
    @DisplayName("一般最坏情况边界值测试")
    @CsvFileSource(resources = "/normal_test_cases.csv")
    void normalWorstTest(int a, int b, int c, String expectancy) {
        System.out.println(a+" "+b+" "+c+" "+expectancy);
        assertEquals(new Triangle(a, b, c).classify(), expectancy);
    }

    @ParameterizedTest
    @DisplayName("健壮最坏情况边界值测试")
    @CsvFileSource(resources = "/strong_test_cases.csv")
    void strongWorstTest(int a, int b, int c, String expectancy) {
        System.out.println(a+" "+b+" "+c+" "+expectancy);
        assertEquals(new Triangle(a, b, c).classify(), expectancy);
    }

}
