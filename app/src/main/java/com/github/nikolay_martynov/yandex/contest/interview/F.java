package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Назовем строку хорошей, если в ней нет двух соседних букв, которые различаются только регистром.
 * Например, строка «abba» хорошая, а строка «aBba» нет.
 * Со строкой можно делать преобразование: если два соседних символа обозначают одну и ту же букву,
 * но записаны в разных регистрах, то их можно удалить.
 * При этом строка «схлопнется», то есть пробелов при удалении не образуется.
 * Цепочкой таких преобразований можно превратить любую строку в хорошую.
 * По заданной строке найдите хорошую строку, в которую ее можно превратить.
 */
public class F {
    static StringBuilder makeBetter(StringBuilder s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char cn = 0;
            if (i < s.length() - 1) {
                cn = s.charAt(i + 1);
                if (Character.toLowerCase(c) != Character.toLowerCase(cn)) {
                    res.append(c);
                } else if (c == cn) {
                    res.append(c);
                } else {
                    i++;
                }
            } else {
                res.append(c);
            }
        }
        return res;
    }

    private static String convertToGoodString(String s) {
        StringBuilder was = new StringBuilder();
        StringBuilder now = new StringBuilder(s);
        while (was.length() != now.length()) {
            was = now;
            now = makeBetter(now);
        }
        return now.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String probablyBadString = reader.readLine().strip();
            System.out.println(convertToGoodString(probablyBadString));
        }
    }
}
