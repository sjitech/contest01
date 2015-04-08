package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/08.
 */
public class Answer2 {

    protected static Logger log = LoggerFactory.getLogger(Answer2.class);

    /**
     * 第一步，分三次把煤运送到中间点B
     * 第二步，分两次把煤运送到中间点C
     * 第三步，把煤运送到目的地D
     * 第一步：5*(AB) = 1000；解得AB=200
     * 第二步：3*BC = 1000；解得BC=333.
     * 第三步：AB+BC+CD=1000；解得CD=467
     * 因此，做多运送533吨煤到目的地
     *
     *
     * 这个问题，首先要考虑什么情况才是最优情况。依照题意，火车只要活动就会消耗煤，所以每次火车出发时只有满载才是最优情况，
     * 并且不能扔掉煤不要。有了这个前提，我们接着往下分析。
     * 这求得最优解的过程肯定要减少火车折返的所消耗的总路程。由于全长1000公里，所以我们肯定需要采用多次中转的方式来运送，
     * 中转的过程中随着煤的消耗，折返的次数也会减少，所以我们要尽量减少多次折返时所前进的距离。
     * 这只有一辆火车，每次出发都是满载，那么我们需要3次将煤运送到下一站，在此期间需要折返5次。
     * 下一站出发如果要满足最优条件，则此期间需要消耗掉一车或者两车煤，根据第二段的分析，我们应该减少5次折返时所前进的距离，
     * 故此次运送需要消耗1车煤。1000/5 = 200公里。
     * 依次类推，第二次运送折返3次，需要消耗1车煤，前进距离为1000/3 = 333.33……公里。
     * 第三次由于仅剩一车煤，满载前进，出去路途消耗，到达终点剩余煤为1000-(1000-(200+333.33) = 533.3333吨。
     *
     * @param totalCoalWeight
     * @param distance
     * @param maxTransportWeight
     * @return
     */
    public int resolve(int totalCoalWeight, int distance, int maxTransportWeight) {
        int n = totalCoalWeight / maxTransportWeight;
        int[] anMidPoint = new int[n];
        int totalDistance = 0;

        for (int i = 0; i < (n - 1); i++) {
            anMidPoint[i] = maxTransportWeight / (2 * (n - i) - 1);
            totalDistance += anMidPoint[i];

            log.debug("point {} distance {}", i, anMidPoint[i]);
        }

        anMidPoint[n - 1] = distance - totalDistance;
        log.debug("point {} distance {}", (n - 1), anMidPoint[n - 1]);

        return (maxTransportWeight - anMidPoint[n - 1]);
    }
}
