package contest.question7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by wang on 2015/04/20.
 */
public class Answer {

    protected static Logger log = LoggerFactory.getLogger(Answer.class);

    public void resolve(int count) {
        if (count < 1 || count > 100) {
            throw new IllegalArgumentException("output count should be 1 ~ 100 integer.");
        }

        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            int[][][] blocks = new int[9][][];

            // blocks[0] blocks[1] blocks[2]
            // blocks[3] blocks[4] blocks[5]
            // blocks[6] blocks[7] blocks[8]
            // start from blocks[4], shift row or column to generate other blocks
            blocks[4] = initSmallBlock();

            blocks[3] = shiftDownBlock(blocks[4]);
            blocks[5] = shiftUpBlock(blocks[4]);
            blocks[1] = shiftRightBlock(blocks[4]);
            blocks[7] = shiftLeftBlock(blocks[4]);
            blocks[0] = shiftRightBlock(blocks[3]);
            blocks[6] = shiftLeftBlock(blocks[3]);
            blocks[2] = shiftRightBlock(blocks[5]);
            blocks[8] = shiftLeftBlock(blocks[5]);

            int[][] answer = merge(blocks);

            log.debug(toLogString(answer));

            // mask 20~55 cells
            int maskCount = rand.nextInt(35) + 20;

            int [][] puzzle = mask(answer, maskCount);

            log.info(toLogString(puzzle));
        }
    }

    protected int[][] merge(int[][][] blocks) {
        int[][] answer = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for(int m = 0; m < 3; m++) {
                for (int k = 0; k < 3; k++) {
                    answer[(i/3)*3 + m][(i%3)*3 + k] = blocks[i][m][k];
                }
            }
        }

        return answer;
    }

    protected int[][] mask(int[][] answer, int maskCount) {
        if (answer == null || answer.length == 0 || answer[0].length == 0) {
            return null;
        }

        if (maskCount == 0) {
            return answer;
        }

        // for simplicity, do not consider difficulty of puzzle
        int rowCount = answer.length;
        int colCount = answer[0].length;
        int[][] ret = new int[rowCount][colCount];

        // copy answer to ret
        for(int m = 0; m < rowCount; m++) {
            for(int n = 0; n < colCount; n++) {
                ret[m][n] = answer[m][n];
            }
        }

        int count = rowCount * colCount;
        Random rand = new Random();
        int masked = -1;

        for (int i = 0; i < maskCount; i++) {
            masked = rand.nextInt(count);

            while (ret[masked/9][masked%9] == 0) {
                // already masked, retry
                masked = rand.nextInt(count);
            }

            ret[masked/9][masked%9] = 0;
        }

        return ret;
    }

    protected int[][] initSmallBlock() {
        List<Integer> values = new ArrayList<>(9);

        for (int i = 1; i < 10; i++) {
            values.add(i);
        }

        Collections.shuffle(values);

        int[][] block = new int[3][3];

        for (int m = 0; m < 9; m++) {
            int row = m / 3;
            int col = m % 3;

            block[row][col] = values.get(m);
        }

        return block;
    }

    protected int[][] shiftDownBlock(int[][] block) {
        if (block == null || block.length == 0 || block[0].length == 0) {
            return null;
        }

        int rowCount = block.length;
        int colCount = block[0].length;

        int[][] ret = new int[rowCount][colCount];

        for (int k = 0; k < rowCount; k++) {
            for(int m = 0; m < colCount; m++) {
                ret[(k + 1) % 3][m] = block[k][m];
            }
        }

        return ret;
    }

    protected int[][] shiftUpBlock(int[][] block) {
        if (block == null || block.length == 0 || block[0].length == 0) {
            return null;
        }

        int rowCount = block.length;
        int colCount = block[0].length;

        int[][] ret = new int[rowCount][colCount];


        for (int k = 0; k < rowCount; k++) {
            for(int m = 0; m < colCount; m++) {
                ret[(k + 2) % 3][m] = block[k][m];
            }
        }

        return ret;
    }

    protected int[][] shiftLeftBlock(int[][] block) {
        if (block == null || block.length == 0 || block[0].length == 0) {
            return null;
        }

        int rowCount = block.length;
        int colCount = block[0].length;

        int[][] ret = new int[rowCount][colCount];


        for (int k = 0; k < rowCount; k++) {
            for(int m = 0; m < colCount; m++) {
                ret[k][(m + 2) % 3] = block[k][m];
            }
        }

        return ret;
    }

    protected int[][] shiftRightBlock(int[][] block) {
        if (block == null || block.length == 0 || block[0].length == 0) {
            return null;
        }

        int rowCount = block.length;
        int colCount = block[0].length;

        int[][] ret = new int[rowCount][colCount];


        for (int k = 0; k < rowCount; k++) {
            for(int m = 0; m < colCount; m++) {
                ret[k][(m + 1) % 3] = block[k][m];
            }
        }

        return ret;
    }

    public String toLogString(int[][] block) {
        if (block == null || block.length == 0 || block[0].length == 0) {
            return "";
        }

        StringBuilder ret = new StringBuilder();

        ret.append(System.lineSeparator());

        int rowCount = block.length;
        int colCount = block[0].length;

        for(int m = 0; m < rowCount; m++) {
            for(int n = 0; n < colCount; n++) {
                ret.append(" " + block[m][n]);
            }

            ret.append(" " + System.lineSeparator());
        }

        return ret.toString();
    }

}
