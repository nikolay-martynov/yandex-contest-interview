package com.github.nikolay_martynov.yandex.contest.interview

import groovy.transform.TupleConstructor
import spock.lang.Specification

class HTest extends Specification {

    @TupleConstructor
    static class State {
        long sum
        List<Integer> heaps
    }

    static long naive(List<Integer> stones) {
        if (stones.size() < 2) {
            return 0
        }
        Deque<State> states = new ArrayDeque<>([new State(0, new ArrayList<Integer>(stones))])
        long minEnergy = Long.MAX_VALUE
        while (!states.empty) {
            State currentState = states.removeLast()
            if (currentState.heaps.size() == 1) {
                minEnergy = Math.min(minEnergy, currentState.sum)
                continue
            }
            for (int i1 = 0; i1 < currentState.heaps.size(); i1++) {
                for (int i2 = i1 + 1; i2 < currentState.heaps.size(); i2++) {
                    int a1 = currentState.heaps.get(i1)
                    int a2 = currentState.heaps.get(i2)
                    List<Integer> newHeaps = new ArrayList<>(currentState.heaps)
                    newHeaps.remove(i1)
                    newHeaps.remove(i2 - 1)
                    newHeaps.add(a1 + a2)
                    State newState = new State(currentState.sum + a1 + a2, newHeaps)
                    states.addLast(newState)
                }
            }
        }
        return minEnergy
    }

    def "naive examples"() {
        expect:
        naive(a) == e
        where:
        a               | e
        [3, 3, 5, 2]    | 26
        [1, 1, 1, 2, 3] | 18
    }

    def "naive same as examples"() {
        expect:
        naive(a) == e
        where:
        a         | e
        [2, 6]    | 8
        [6, 2, 4] | 18
    }

    def "same as naive"() {
        given:
        Random r = new Random()
        List<Integer> heaps = (0..7).collect { r.nextInt(100000000) }
        when:
        long naive = naive(heaps)
        long algorithmic = H.getEnergyForUnion(heaps)
        then:
        naive == algorithmic
        cleanup:
        println heaps
        println "naive $naive algorithmic $algorithmic"
    }

    def "examples"() {
        expect:
        H.getEnergyForUnion(a) == e
        where:
        a                  | e
        [2, 6]             | 8
        [6, 2, 4]          | 18
        [1, 1, 1]          | 5
        [1, 1, 1, 1]       | 8
        [1, 2, 3, 4, 5]    | 33
        [1, 1, 2, 2, 3, 3] | 30
        [3, 3, 2, 2, 1, 1] | 30
        [10]               | 0
    }

}
