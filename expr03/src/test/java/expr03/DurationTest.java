package expr03;

import com.ctgu.expr03.CallBill;
import com.ctgu.expr03.CallDuration;
import com.ctgu.expr03.WrongDurationException;
import com.ctgu.expr03.timeCrossType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DurationTest {
    //1677600000000 -- 2023-03-01 00:00:00
    //1677614400000 -- 2023-03-01 04:00:00
    //1677606300000 -- 2023-03-01 01:45:00
    //1677608100000 -- 2023-03-01 02:15:00
    //1677722400000 -- 2023-03-02 10:00:00
    //1677706200000 -- 2023-03-02 05:30:00
    //1677713400000 -- 2023-03-02 07:30:00

    //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-03-01 00:00:00");
    //System.out.println(date.getTime());


    @ParameterizedTest
    @CsvFileSource(resources = "/DurationValidTestCases.csv")
    void validTest(long start_time, long end_time, timeCrossType type, float expection) throws WrongDurationException {
        assertEquals(new CallBill(new CallDuration(start_time, end_time, type)).getCost(), expection);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DurationInvalidTestCases.csv")
    void invalidTest(long start_time, long end_time, timeCrossType type, float expection) throws WrongDurationException {
        assertEquals(new CallBill(new CallDuration(start_time, end_time, type)).getCost(), expection);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/DurationExceptionalTestCases.csv")
    void exceptionalTest(long start_time, long end_time, timeCrossType type) {
        assertThrows(WrongDurationException.class, () -> {
            double cost = new CallBill(new CallDuration(start_time, end_time, type)).getCost();
        });
    }

/*    @ParameterizedTest
    @CsvSource("1677600000000, 1677614400000, ABNORMAL")
    void wrongParamsTest(long start_time, long end_time, timeCrossType type) throws WrongDurationException {
        double cost = new CallBill(new CallDuration(start_time, end_time, type)).getCost();
    }*/
}
