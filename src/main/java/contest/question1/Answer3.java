package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/08.
 */
public class Answer3 {

    protected static Logger log = LoggerFactory.getLogger(Answer3.class);

    /**
     * 一公里一公里的向前移动
     * 在3000 – 2000 时，每向前一公里，需要来回移动5次，故在这个时段每公里耗费5吨煤
     * 在2000 – 1000 时，每向前一公里，需要来回移动3次，每公里耗费3吨煤
     * 在1000 – 0 时，每公里1吨煤
     * 所以结果如下：
     * 剩下的煤 已经走了的公里数
     * 3000 : 0
     * 2000 : (1000/5) = 200
     * 1001 : 200 + (1000/3) = 533
     * 最后在走了533公里的时候，火车上还可以有1001吨，但是零下的一吨是带不走的
     * 所以最后应该是 1000吨 – （1000公里 – 533公里）* 1吨每公里 = 533吨
     *
     * @param coal
     * @param capacity
     * @param distance
     * @return
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
