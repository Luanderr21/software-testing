package expr04;

import com.ctgu.expr04.CallingBill;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.LocalDateTime;

public class CallingTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/DecisionTable.csv")
    void converseTest(
            @JavaTimeConversionPattern("yyyy-MM-dd HH:mm:ss")
                    LocalDateTime dateTime,
            @JavaTimeConversionPattern("yyyy-MM-dd HH:mm:ss")
                    LocalDateTime dateTime2) {
        CallingBill bill = new CallingBill(dateTime, dateTime2);
        System.out.println(bill.calculateFee());
    }


}
