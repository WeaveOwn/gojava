package com.weaveown.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwei
 * @date 2020/9/2
 */
public class Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        int min = Math.min(s.length(), numRows);
        for (int i = 0; i < min; i++) {
            rows.add(new StringBuilder());
        }
        char[] chars = s.toCharArray();
        int curRow = 0;
        boolean turn = false;
        for (char a: chars) {
            rows.get(curRow).append(a);
            if (curRow == 0 || min -1 == curRow) {
                turn = !turn;
            }
            curRow += turn ? 1 : -1;
        }
        String result = "";
        for (StringBuilder row : rows) {
            result += row.toString();
        }
        return result;
    }
    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int addLen = numRows * 2 - 2;
        int length = chars.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < length; j += addLen) {
                result.append(chars[j + i]);
                if (i !=0 && i != numRows - 1 && j + addLen - i < length) {
                    result.append(chars[j + addLen - i]);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Convert().convert("LEETCODEISHIRING", 3));
        System.out.println(new Convert().convert2("LEETCODEISHIRING", 3));
    }


}
