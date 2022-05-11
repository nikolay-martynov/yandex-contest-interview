package com.github.nikolay_martynov.yandex.contest.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Вам дана матрица из n строк и m столбцов, заполненная натуральными числами.
 * По матрице можно перемещаться, из клетки можно уходить только в соседнюю по стороне клетку,
 * переходы по диагонали, а также выход за границу матрицы запрещены.
 * Ваша задача — найти наиболее длинный возрастающий путь в матрице.
 * Путь возрастающий, если значения в посещаемых клетках строго возрастают от начала пути к его концу.
 */
public class D {

    static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static List<Position> paths(int r, int c, List<List<Integer>> matrix) {
        List<Position> result = new ArrayList<>(4);
        if (r > 0) {
            if (matrix.get(r).get(c) < matrix.get(r - 1).get(c)) {
                result.add(new Position(r - 1, c));
            }
        }
        if (r < matrix.size() - 1) {
            if (matrix.get(r).get(c) < matrix.get(r + 1).get(c)) {
                result.add(new Position(r + 1, c));
            }
        }
        if (c > 0) {
            if (matrix.get(r).get(c) < matrix.get(r).get(c - 1)) {
                result.add(new Position(r, c - 1));
            }
        }
        if (c < matrix.get(r).size() - 1) {
            if (matrix.get(r).get(c) < matrix.get(r).get(c + 1)) {
                result.add(new Position(r, c + 1));
            }
        }
        return result;
    }

    static int findLongestPathFor(int r, int c, List<List<Integer>> matrix, int[][] lengths) {
        if (lengths[r][c] > 0) {
            return lengths[r][c];
        }
        int maxChild = 0;
        for (Position path : paths(r, c, matrix)) {
            maxChild = Math.max(maxChild, findLongestPathFor(path.r, path.c, matrix, lengths));
        }
        lengths[r][c] = 1 + maxChild;
        return lengths[r][c];
    }

    private static int getLongestIncreasingPath(List<List<Integer>> matrix) {
        if (matrix.isEmpty() || matrix.get(1).isEmpty()) {
            return 0;
        }
        int[][] lengths = new int[matrix.size()][matrix.get(1).size()];
        int longest = 0;
        for (int r = 0; r < matrix.size(); r++) {
            for (int c = 0; c < matrix.get(r).size(); c++) {
                longest = Math.max(longest, findLongestPathFor(r, c, matrix, lengths));
            }
        }
        return longest;
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader) throws IOException {
        String[] sizes = reader.readLine().strip().split(" ");
        int n = Integer.parseInt(sizes[0]);
        List<List<Integer>> matrix = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" ")).stream().map(token -> Integer.parseInt(token)).collect(Collectors.toList());
    }

}
