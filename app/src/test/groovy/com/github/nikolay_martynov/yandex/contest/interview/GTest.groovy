package com.github.nikolay_martynov.yandex.contest.interview

import org.spockframework.util.StringMessagePrintStream
import spock.lang.Specification

class GTest extends Specification {

    def "parent duplication"() {
        expect:
        G.getNumberOfUpgoingPaths(t, t.length, x) == c
        where:
        t                                            | x | c
        [[-1, 0], [0, 1], [1, 0], [1, 0]] as int[][] | 1 | 6
    }

    def "read input"() {
        given:
        InputStream sin = System.in
        OutputStream sout = System.out
        System.setIn(new ByteArrayInputStream("""6 3
-1 1
0 1
0 1
1 1
2 2
3 1
""".getBytes()))
        List<String> out = []
        System.setOut(new StringMessagePrintStream() {
            @Override
            protected void printed(String message) {
                out << message
            }
        });
        when:
        G.main(new String[0])
        then:
        out == ["3" + System.lineSeparator()]
        cleanup:
        System.setIn(sin)
        System.setOut(sout)
    }

    def "examples"() {
        expect:
        G.getNumberOfUpgoingPaths(t, t.length, x) == c
        where:
        t                                                            | x | c
        [[-1, 1], [0, 1], [0, 1], [1, 1], [2, 2], [3, 1]] as int[][] | 3 | 3
        [[-1, 1]] as int[][]                                         | 2 | 0
        [[-1, 1]] as int[][]                                         | 1 | 1
    }

    def "random nodes order"() {
        expect:
        G.getNumberOfUpgoingPaths(t, t.length, x) == c
        where:
        t            | x | c
        [
                [1, 1],
                [2, 1],
                [3, 1],
                [-1, 1],
                [3, 1],
                [4, 2],
        ] as int[][] | 3 | 3
    }

    def "negative weight"() {
        expect:
        G.getNumberOfUpgoingPaths(t, t.length, x) == c
        where:
        t                                                                  | x  | c
        [[-1, -1], [0, -1], [0, -1], [1, -1], [2, -2], [3, -1]] as int[][] | -3 | 3
    }

    def "zero weight as sum"() {
        expect:
        G.getNumberOfUpgoingPaths(t, t.length, x) == c
        where:
        t                                                              | x | c
        [[-1, 0], [0, -1], [0, -1], [1, 1], [2, 1], [3, 0]] as int[][] | 0 | 8
    }

    def "larger tree"() {
        given:
        int[][] tree = [
                [-1, 1],
                [0, 1],
                [0, 1],
                [0, 1],
                [1, 7],
                [1, 6],
                [1, 1],
                [2, 1],
                [2, 2],
                [2, 1],
                [3, 1],
                [3, 1],
                [3, 2],
                [4, 2],
                [5, 2],
                [6, 1],
                [7, 2],
                [8, 1],
                [8, 1],
                [10, 1],
                [10, 2],
                [11, 21],
                [17, 2],
                [17, 2],
                [17, 2],
                [18, 1],
                [18, 2],
                [18, 1],
                [23, 2],
                [23, 2],
                [24, 1],
                [24, 1],
                [26, 2],
                [26, 2],
                [26, 1],
        ]
        expect:
        G.getNumberOfUpgoingPaths(tree, tree.length, x) == c
        where:
        x  | c
        10 | 2
        8  | 10
        21 | 1
        1  | 18
    }

    def "zero weight"() {
        given:
        int[][] tree = [
                [-1, 0],
                [0, 0],
                [0, 1],
                [1, 1],
                [1, 1],
                [2, 0],
                [2, 0],
        ]
        expect:
        G.getNumberOfUpgoingPaths(tree, tree.length, x) == c
        where:
        x | c
        1 | 12
    }

    def "empty"() {
        when:
        int result = G.getNumberOfUpgoingPaths([] as int[][], 0, 1)
        then:
        result == 0
    }
}
