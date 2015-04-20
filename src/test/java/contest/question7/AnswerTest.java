package contest.question7;

import contest.question2.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/20.
 */
public class AnswerTest {

    protected static Logger log = LoggerFactory.getLogger(AnswerTest.class);

    @Test
    public void testResolve() {
        log.info("start resolve {}", this.getClass().getName());

        Answer answer = new Answer();

        answer.resolve(10);
    }
}
