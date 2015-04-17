package contest.question6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/17.
 */
public class Answer2 {
    protected static Logger log = LoggerFactory.getLogger(Answer2.class);

    /**
     * ret = [k/5] + [k/(5^2)] + [k/(5^3)] + ...
     *
     * @param k
     * @return
     */
    public long resolve(long k) {
        long ret = 0L;
        long n = k;

        while(n > 0) {
            ret += n / 5;
            n /= 5;
        }

        return ret;
    }
}
