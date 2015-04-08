package contest.question2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/08.
 */
public class Answer {

    protected static Logger log = LoggerFactory.getLogger(Answer.class);


    public String multiply(String op1, String op2) {
        int[] op1Num = parseOp(op1);
        int[] op2Num = parseOp(op2);

        int op1Len = op1Num.length;
        int op2Len = op2Num.length;

        int[] result = new int[op1Len + op2Len];

        int carry = 0;

        for (int i = 0; i < op1Len; i++) {
            for (int k = 0; k < op2Len; k++) {

            }
        }

        return null;
    }


    private int[] parseOp(String op1) {
        if (op1 ==  null || op1.length() == 0 || op1.length() > 10000) {
            throw new IllegalArgumentException("require 10000-digit number for operator");
        }

        int len = op1.length();
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            // reverse digit order
            result[len - 1 - i] = Integer.parseInt(op1.substring(i, i + 1));
        }

        return result;
    }
}
