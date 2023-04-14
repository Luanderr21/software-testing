package com.ctgu.expr05;

import com.ctgu.expr05.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveTest {
    class TestCase {
        LinkedList<Integer> testList;
        Integer o;

         TestCase(LinkedList<Integer> testList, Integer o) {
            this.testList = testList;
            this.o = o;
        }
    }

    LinkedList<TestCase> getCases(){
        LinkedList<TestCase> res = new LinkedList<>();
        LinkedList<Integer> p1_l = new LinkedList<>();
        p1_l.add(null);
        p1_l.add(1);
        TestCase p1 = new TestCase(p1_l, null);
        res.add(p1);

        LinkedList<Integer> p2_l = new LinkedList<>();
        p2_l.add(1);
        p2_l.add(null);
        TestCase p2 = new TestCase(p2_l, null);
        res.add(p2);

        LinkedList<Integer> p3_l = new LinkedList<>();
        p3_l.add(null);
        TestCase p3 = new TestCase(p3_l, null);
        res.add(p3);

        LinkedList<Integer> p4_l = new LinkedList<>();
        p4_l.add(1);
        p4_l.add(2);
        TestCase p4 = new TestCase(p4_l, 1);
        res.add(p4);

        LinkedList<Integer> p5_l = new LinkedList<>();
        p5_l.add(1);
        p5_l.add(2);
        TestCase p5 = new TestCase(p5_l, 2);
        res.add(p5);

        LinkedList<Integer> p6_l = new LinkedList<>();
        p6_l.add(null);
        TestCase p6 = new TestCase(p6_l, 1);
        res.add(p6);

        return res;
    }
    @Test
    void test(){
        int caseIndex = 1;
        Boolean[] expectancy = {null, true, true, true, true, true, false};
        for (TestCase testCase:
             getCases()) {
            System.out.println("TestCase: p"+caseIndex);
            System.out.println("Before Removed --- list:"+testCase.testList+" o:"+testCase.o);
            Boolean result = testCase.testList.remove(testCase.o);
            System.out.println("Removed Result --- "+result);
            System.out.println("After Removed --- list:"+testCase.testList+" o:"+testCase.o);
            System.out.println("-----------------------");
            assertEquals(expectancy[caseIndex], result);
            caseIndex++;
        }
    }
}
