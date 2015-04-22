package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/08.
 */
public class Answer3 {

    protected static Logger log = LoggerFactory.getLogger(Answer3.class);

    /**
     *
     * @param coal
     * @param capacity
     * @param distance
     * @return 最大運送できる石炭量
     */
    public int resolve(int coal, int capacity, int distance) {
        int milesLeft = distance;
        int trainCapacity = capacity;
        int coalLeft = coal;

        int backAndForthCount = coalLeft / milesLeft + (((coalLeft % milesLeft) > 0) ? 1 : 0);
        int backAndForthTimes = 2 * backAndForthCount - 1;

        while (backAndForthTimes > 1) {
            int costPerMile = backAndForthTimes;
            int milesToBeMoved = capacity / costPerMile;

            coalLeft -= milesToBeMoved * costPerMile;
            milesLeft -= milesToBeMoved;

            backAndForthCount--;
            backAndForthTimes = 2 * backAndForthCount - 1;
        }

        return trainCapacity - milesLeft;
    }
}
