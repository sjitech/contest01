package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/08.
 */
public class Answer2 {

    protected static Logger log = LoggerFactory.getLogger(Answer2.class);

    /**
     *     |-----------------|-----------------|----------------------------------------------|
     *   出発点A             B(中間点)          C(中間点)                                      D(終点)
     *
     *   1. 最適化のため､BとCを出発の時､満載(1000トン)がほしい
     *   2. 1kmは1トンの石炭を消費するので､最低3段(3000/1000)が方しい(4段なら､無駄な消費がある)
     *
     *   そして､Bは2000トン､Cは1000トンの石炭を残りる
     *
     *   5*(AB) = 1000 (往復5回､1000トンの石炭を消費する)
     *   3*(BC) = 1000 (往復3回､1000トンの石炭を消費する)
     *   AD = AB + BC + CD = 1000
     *
     *   CD = 467
     *   Dに最大運送石炭 = 1000 - 467 = 533
     *
     * @param totalCoalWeight
     * @param distance
     * @param maxTransportWeight
     * @return 最大運送できる石炭量
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
