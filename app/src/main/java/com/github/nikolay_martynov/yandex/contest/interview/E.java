package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

/**
 * Вам дано число, записанное римскими цифрами.
 * Получите это же число в обычной записи (арабскими цифрами).
 * Римская запись чисел может включать следующие символы:
 * ’I’ — 1
 * ’V’ — 5
 * ’X’ — 10
 * ’L’ — 50
 * ’C’ — 100
 * ’D’ — 500
 * ’M’ — 1000
 * Цифры ’I’, ’X’, ’C’ и ’M’ нельзя использовать более трех раз подряд.
 * Цифры ’V’, ’L’ и ’D’ нельзя использовать более одного раза во всей записи числа.
 * Обыкновенно цифры записывают по убыванию слева направо.
 * Например, число 350 будет записано как ’CCCL’.
 * Однако есть исключения:
 * Чтобы получить ’4’ или ’9’, надо поставить ’I’ перед ’V’ или ’X’ соответственно.
 * Чтобы получить ’40’ или ’90’, надо поставить ’X’ перед ’L’ или ’C’.
 * Чтобы получить ’400’ или ’900’, надо поставить ’C’ перед ’D’ или ’M’.
 */

public class E {

    private static final Map<Character, Integer> conv = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    static int convertToArabic(String s) {
        if (s.isEmpty()) {
            return -1;
        }
        Map<Character, Integer> counts = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (conv.get(c) == null) {
                return -1;
            }
            char cn = 0;
            if (i < s.length() - 1) {
                cn = s.charAt(i + 1);
                if (conv.get(cn) == null) {
                    return -1;
                }
            }
            if (c == 'I' || c == 'X' || c == 'C' || c == 'M') {
                if (i == 0 || c != s.charAt(i - 1)) {
                    counts.put(c, 1);
                } else {
                    counts.put(c, 1 + counts.getOrDefault(c, 0));
                }
                if (counts.get(c) > 3) {
                    return -1;
                }
            } else if (c == 'V' || c == 'L' || c == 'D') {
                if (counts.get(c) != null) {
                    return -1;
                }
                counts.put(c, 1);
            }
            if (cn > 0 && conv.get(c) < conv.get(cn)) {
                res -= conv.get(c);
            } else {
                res += conv.get(c);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String romanNumber = reader.readLine().strip();
            System.out.println(convertToArabic(romanNumber));
        }
    }
}
