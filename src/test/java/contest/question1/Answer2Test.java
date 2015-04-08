package contest.question1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/08.
 */
public class Answer2Test {
    protected static Logger log = LoggerFactory.getLogger(Answer1Test.class);

    @Test
    public void testResolve() {
        log.info("start resolve {}", this.getClass().getName());

        Answer2 answer = new Answer2();

        log.info("Max transport coal weight is : " + answer.resolve(3000, 1000, 1000));
    }
}
