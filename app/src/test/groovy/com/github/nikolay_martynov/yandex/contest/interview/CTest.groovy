package com.github.nikolay_martynov.yandex.contest.interview

import spock.lang.Specification

class CTest extends Specification {

    static long[] fac = new long[21];

    def setupSpec() {
        fac[0] = 1
        fac[1] = 1
        for (int i = 2; i <= fac.length - 1; i++) {
            fac[i] = fac[i - 1] * i
        }
    }

    long combinations(int n, int by) {
        if (n < by) {
            return 0
        }
        fac[n] / fac[by] / fac[n - by]
    }

    def setup() {
        C.pairsCache = new ArrayList<>();
    }

    def "getPairs sequential"() {
        expect:
        C.pairs(n) == c
        where:
        n | c
        0 | 0
        1 | 0
        2 | 1
        3 | 3
        4 | 6
        5 | 10
        6 | 15
        7 | 21
    }

    def "getPairs random"() {
        expect:
        C.pairs(n) == c
        where:
        n | c
        6 | 15
        3 | 3
        5 | 10
        1 | 0
        0 | 0
        2 | 1
        4 | 6
        7 | 21
    }

    def "pairs via formula"() {
        when:
        List<Long> viaFormula = []
        List<Long> iterative = []
        for (int n = 1; n <= fac.length - 1; n++) {
            viaFormula << combinations(n, 2)
            iterative << C.pairs(n)
        }
        then:
        viaFormula == iterative
    }

    def "examples"() {
        expect:
        C.getNumberOfGoodPairs(a.size(), a) == c
        where:
        a                           | c
        [203, 404, 204, 200, 403]   | 2
        [1000000]                   | 0
        [2022, 2020, 2021]          | 0
        [1, 2, 3, 3, 2, 1, 1, 2, 3] | 9
    }

}
