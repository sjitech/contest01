package contest.question2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        // init result
        for (int i = 0; i < op1Len + op2Len; i++) {
            result[i] = -1;
        }

        //log.debug("{} x {}", int2str(op1Num), int2str(op2Num));

        int carry = 0;
        int temp = 0;

        for (int i = 0; i < op1Len; i++) {
            carry = 0;

            for (int k = 0; k < op2Len; k++) {
                temp = ((result[i + k] > 0) ? result[i + k] : 0) + op1Num[i] * op2Num[k] + carry;

                if (temp > 9) {
                    carry = temp / 10;
                    result[i + k] = temp % 10;
                } else {
                    carry = 0;
                    result[i + k] = temp;
                }

                //log.debug("{} x {} = {}, carry {}", op1Num[i], op2Num[k], temp, carry);
            }

            if (carry > 0) {
                result[i + op2Len] = carry;
            }

            //log.debug("{} : {}", i, int2str(result));
        }

        List<String> ret = new ArrayList<>();

        for (int i = 0; i < op1Len + op2Len; i++) {
            if (result[i] == -1) {
                break;
            }

            ret.add("" + result[i]);
        }

        Collections.reverse(ret);

        StringBuilder buf = new StringBuilder();

        for(String ch : ret) {
            buf.append(ch);
        }

        return buf.toString();
    }

    private String int2str(int[] source) {
        if (source == null || source.length == 0) {
            return "[]";
        }

        StringBuilder buf = new StringBuilder();

        int len = source.length;

        buf.append("[");
        for (int i = 0; i < len; i++) {
            buf.append(source[i]);
        }
        buf.append("]");

        return buf.toString();
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
