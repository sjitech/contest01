package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/07.
 */
public class Answer1 {

    protected static Logger log = LoggerFactory.getLogger(Answer1.class);

    /**
     * 往復で途中のどこかに卸して､最後に1000また1000未満の石炭を載って､終点に行く｡
     * 石炭運送の消費(運送コスト)は以下の公式で計算できる｡
     * n = (残りの石炭量 - 1) / 1000, 距離dで卸す場合､石炭の消費は (2n + 1) * dである｡
     *
     * 2000~3000の場合､コストは5*dである
     * 1000~2000の場合､コストは3*dである｡
     * 1000以内の場合､コストは1*dである｡(全ての石炭を載って､終点に行く)
     *
     * dが1~500kmを試して､最大の結果を探す
     *
     * @return 最大運送できる石炭量
     */
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

        if ((coalWeight % 1000) > (2 * distance)) {
            amount += coalWeight % 1000 - distance;
        } else {
            amount += distance;
        }

        return transport(distance, amount, remainDistance - distance);
    }
}
