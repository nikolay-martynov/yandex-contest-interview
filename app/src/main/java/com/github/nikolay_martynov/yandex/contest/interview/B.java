package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * На стол в ряд выложены карточки, на каждой карточке написано натуральное число.
 * За один ход разрешается взять карточку либо с левого, либо с правого конца ряда.
 * Всего можно сделать k ходов.
 * Итоговый счет равен сумме чисел на выбранных карточках.
 * Определите, какой максимальный счет можно получить по итогам игры.
 */
public class B {

    private static long getCardCount(int n, int k, List<Long> cards) {
        long maxSum = 0;
        long[] sumLeft = new long[n + 1];
        long[] sumRight = new long[n + 1];
        for (int len = 1; len <= n; len++) {
            sumLeft[len] = sumLeft[len - 1] + cards.get(len - 1);
            sumRight[len] = sumRight[len - 1] + cards.get(n - len);
        }
        for (int prefixLen = 0; prefixLen <= Integer.min(k, n); prefixLen++) {
            int suffixLen = k - prefixLen;
            long sum = sumLeft[prefixLen] + sumRight[suffixLen];
            maxSum = Long.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int k = readInt(reader);
            List<Long> cards = readList(reader);

            System.out.println(getCardCount(n, k, cards));
        }
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" ")).stream().map(token -> Long.parseLong(token)).collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }

}
