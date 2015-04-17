package contest.question6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/17.
 */
public class Answer1 {
    protected static Logger log = LoggerFactory.getLogger(Answer1.class);

    /**
     * N! = (2^x) * (3^y) * (5^z) ...
     * 10 = 2 * 5
     * ret = min(x, z)
     * x > z, ret = z
     *
     * @param k
     * @return
     */
    public long resolve(long k) {
        long ret = 0L;
        long j = 0;

        for(long i = 1; i <= k; i++) {
            j = i;

            while((j % 5) == 0) {
                ret++;
                j /= 5;
            }
        }

        return ret;
    }
}
