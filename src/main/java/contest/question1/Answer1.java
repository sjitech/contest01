package contest.question1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/07.
 */
public class Answer1 {

    protected static Logger log = LoggerFactory.getLogger(Answer1.class);

    /**
     * 1000是基本刻度，解题精髓在于消耗多余的煤把煤移动到一个离终点更近的距离，最后一次运上1000吨然后从这个地方直达终点，
     * 还能剩下的就是你能运达的煤。煤越多需要搬运的次数越多，次数越多消耗就越多。
     * 要运送到相对较近的距离对煤的消耗量为(2n + 1)*Step，Step为一次行进的步长，n=(总煤量 – 1) / 1000,
     * 根据这个公式，
     * 当煤量为2000～3000时，你的运送成本是5*Step
     * 1000～2000时为3*Step，
     * 小于等于1000时为1*Step,
     *
     * 所以第一次选择行进200公里，消耗的煤为5 × 200 = 1000，剩余2000（2000～3000）
     * 第二次选择行进333公里，消耗的煤为3 × 333 = 999 =～ 1000，剩余1000（1000～2000）
     * 此时一共行进了533公里，还剩下1000吨煤，最后到达终点还剩533吨（1000内）
     *
     * 其实次数是无所谓的，关键是把握消耗的数量级。看把第二个刻度分解了
     * 第一次选择行进200公里，消耗的煤为5 × 200 = 1000，剩余2000（2000～3000）
     * 第二次选择行进200公里，消耗的煤为3 × 200 = 600，剩余1400（1000～2000）
     * 第三次选择行进100公里，消耗的煤为3 × 100 = 300， 剩余1100（1000～2000）
     * 第四次选择行进33公里，消耗3×33=99～=100，剩余1000（1000～2000）
     * 此时一共行进了533公里，还剩下1000吨煤，最后到达终点还剩533吨（1000内）
     * 此题具备高中数学知识就够解了，我发现算法其实很多时候要求的数学知识并不是非常精深，很复杂的数学问题的解也许是一个很简单的方程。
     * 思考大于知识。
     *
     *
     * @return
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
