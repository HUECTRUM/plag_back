package com.nothing.data

import groovy.transform.Canonical

@Canonical class Pair {
    String lId, lFile, rId, rFile
    double similarity
    int totOverlap, longestOverlap
}
