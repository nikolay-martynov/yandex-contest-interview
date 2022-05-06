package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Статус 200.
 * Вам дан массив натуральных чисел a[i].
 * Найдите число таких пар элементов (a[i], a[j]), где ∣ a[i] − a[j] ∣ %200 == 0 и i < j.
 */
public class C {

    static ArrayList<Long> pairsCache = new ArrayList<>();

    static long pairs(int numbers) {
        if (pairsCache.size() < 3) {
            pairsCache.add(0L);
            pairsCache.add(0L);
            pairsCache.add(1L);
        }
        if (pairsCache.size() > numbers) {
            return pairsCache.get(numbers);
        }
        pairsCache.ensureCapacity(numbers);
        long result = pairs(numbers - 1) + numbers - 1;
        pairsCache.add(result);
        return result;
    }

    static long getNumberOfGoodPairs(int n, List<Integer> a) {
        if (n != a.size()) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        int[] mods = new int[200];
        for (int number : a) {
            int mod = number % 200;
            mods[mod] = mods[mod] + 1;
        }
        for (int i = 0; i < 200; i++) {
            count += pairs(mods[i]);
        }
        return count;
    }

    static long naiveSolution(int n, List<Integer> a) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    if (Math.abs(a.get(i) - a.get(j)) % 200 == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> numbers = readList(reader);
            System.out.println(getNumberOfGoodPairs(n, numbers));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
                .stream()
                .map(token -> Integer.parseInt(token))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws NumberFormatException, IOException {
        return Integer.parseInt(reader.readLine());
    }
}
