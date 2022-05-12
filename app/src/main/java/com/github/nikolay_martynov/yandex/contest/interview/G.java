package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Дано укоренённое дерево на N вершинах и число X.
 * В каждой вершине записано число — её вес.
 * Назовём восходящим путь ai,p(ai),p(p(ai)),..., где p(a) — родитель вершины a.
 * Проще говоря, восходящий путь — это путь, который начинается с некоторой вершины и двигается в сторону корня
 * (не обязательно доходя до него).
 * Путь может состоять из одной вершины.
 * Весом пути назовём суммарный вес вершин на этом пути.
 * Найдите количество восходящих путей с весом X.
 */
public class G {

    static int getNumberOfUpgoingPaths(int[][] tree, int n, int x) {
        int count = 0;
        boolean[] counted = new boolean[n];
        for (int nodeCursorIndex = 0; nodeCursorIndex < n; nodeCursorIndex++) {
            int beginNodeIndex = nodeCursorIndex;
            // A path is from current node to root.
            // If the next beginning has already been counted then avoid duplication.
            while (beginNodeIndex != -1 && !counted[beginNodeIndex]) {
                counted[beginNodeIndex] = true;
                int[] beginNode = tree[beginNodeIndex];
                int sum = 0;
                int endNodeIndex = beginNodeIndex;
                while (endNodeIndex != -1) {
                    int[] endNode = tree[endNodeIndex];
                    sum += beginNodeIndex == endNodeIndex ? beginNode[1] : endNode[1];
                    if (sum == x) {
                        count++;
                    }
                    endNodeIndex = endNode[0];
                }
                beginNodeIndex = beginNode[0];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int[][] tree = new int[n][2];
            for (int i = 0; i < n; i++) {
                int[] node = tree[i];
                node[0] = scanner.nextInt();
                node[1] = scanner.nextInt();
            }
            System.out.println(getNumberOfUpgoingPaths(tree, n, x));
        }
    }

}