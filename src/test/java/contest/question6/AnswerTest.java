package contest.question6;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/17.
 */
public class AnswerTest {
    protected static Logger log = LoggerFactory.getLogger(AnswerTest.class);

    @Test
    public void testResolve() {
        log.info("start resolve {}", this.getClass().getName());

        Answer1 answer1 = new Answer1();
        Answer2 answer2 = new Answer2();

        long k = 1308888770L;

        log.info("answer1 resolve " + k + "! : " + answer1.resolve(k));
        log.info("answer2 resolve " + k + "! : " + answer2.resolve(k));
    }
}
