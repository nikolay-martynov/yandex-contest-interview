package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * В этой задаче вы будете перекладывать камни.
 * Изначально есть n кучек камней.
 * Кучка i весит ai килограммов.
 * Кучки можно объединять.
 * При объединении кучек i и j затрачивается ai+aj единиц энергии,
 * при этом две исходные кучки пропадают и появляется кучка весом ai+aj.
 * Определите наименьшее количество энергии, которое надо затратить
 * для объединения всех кучек в одну.
 */
public class H {

    static long getEnergyForUnion(List<Integer> stones) {
        PriorityQueue<Integer> q = new PriorityQueue<>(stones);
        long sum = 0;
        while (q.size() >= 2) {
            int a1 = q.remove();
            int a2 = q.remove();
            sum += a1 + a2;
            q.add(a1 + a2);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> stones = readList(reader);

            System.out.println(getEnergyForUnion(stones));
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
