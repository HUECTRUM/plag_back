package com.nothing.extensions

import spock.lang.Specification

class GenerateUntilEmptyTests extends Specification {
    def testGenerateUntilEmpty() {
        expect:
        [0,1,2,3] == [0, 1, 2, 3]
    }

    static def generator(int i) {
        return 0..i
                .collect { it }
    }
}
