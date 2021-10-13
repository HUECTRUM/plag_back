package com.nothing.extensions

import spock.lang.Specification

class FlattenOnceTests extends Specification {
    def testFlatten(List<Integer> original, List<Integer> flattened) {
        expect:
        original.flattenOnce() == flattened

        where:
        original                         | flattened
        [3]                              | [3]
        [[3]]                            | [3]
        [[[3]]]                          | [[3]]
        [3, 4]                           | [3, 4]
        [3, [4]]                         | [3, 4]
        [[3], [4]]                       | [3, 4]
        [[[3]], [4]]                     | [[3], 4]
        [[[3]], [[4]]]                   | [[3], [4]]
        [3, 4, 5]                        | [3, 4, 5]
        [3, [4, 5]]                      | [3, 4, 5]
        [3, [[4, 5]]]                    | [3, [4, 5]]
        [3, [4, [5]]]                    | [3, 4, [5]]
        [3, [[4, [5]]]]                  | [3, [4, [5]]]
        [1, [2, 3], [[4]], [5, [6]]]     | [1, 2, 3, [4], 5, [6]]
    }
}
