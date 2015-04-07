package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/07.
 */
public class Answer {

    protected static Logger log = LoggerFactory.getLogger(Answer.class);

    public int resolve() {
        int best = 0;
        int temp = 0;

        for(int i = 1; i < 500; i++) {
            temp = transport(i, 3000, 1000);

            log.debug("when drop in {}, will transport {}", i, temp);

            if (temp > best) {
                best = temp;
            }
        }

        return best;
    }

    public int transport(int distance, int coalWeight, int remainDistance) {
        if (coalWeight <= 1000) {
            return coalWeight - remainDistance;
        }

        int left = 1000 - distance * 2;
        int amount = (coalWeight/1000) * left;

        if ((coalWeight%1000) > (2 * distance)) {
            amount += coalWeight%1000 - distance;
        } else {
            amount += distance;
        }

        return transport(distance, amount, remainDistance - distance);
    }
}
