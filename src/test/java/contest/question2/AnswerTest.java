package contest.question2;

import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Created by wang on 2015/04/08.
 */
public class AnswerTest {

    protected static Logger log = LoggerFactory.getLogger(AnswerTest.class);

    @Test
    public void testMultiply() {
        log.info("start resolve {}", this.getClass().getName());

        String op1 = "2898834383";
        String op2 = "2839023948844828479282838";

        Answer answer = new Answer();
        String actual = answer.multiply(op1, op2);

        BigInteger op1Bi = new BigInteger(op1);
        BigInteger op2Bi = new BigInteger(op2);

        String expected = op1Bi.multiply(op2Bi).toString();

        log.debug("{} x {} actual: {} expected: {}", op1, op2, actual, expected);

        assertEquals("test multiply function", expected, actual);
    }
}
