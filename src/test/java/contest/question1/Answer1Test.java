package contest.question1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by wang on 2015/04/07.
 */
public class Answer1Test {
    protected static Logger log = LoggerFactory.getLogger(Answer1Test.class);

    @Test
    public void testResolve() {
        log.info("start resolve {}", this.getClass().getName());

        Answer1 answer = new Answer1();

        log.info("Max transport coal weight is : " + answer.resolve());
    }
}
