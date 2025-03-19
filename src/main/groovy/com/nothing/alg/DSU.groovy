package com.nothing.alg

class DSU {
    List<Integer> par, sz
    List<List> data

    DSU(int n) {
        (par, sz, data) = [new ArrayList<>(), new ArrayList<>(), new ArrayList<>()]
        (0..<n).each { par += it; sz += 1; data.add([]) }
    }

    int find(int x) {
        return x == par[x] ? x : (par[x] = find(par[x]))
    }

    boolean unite(int a, int b, def attachA, def attachB) {
        (a, b) = [find(a), find(b)]

        if (a == b) return false
        if (sz[a] < sz[b]) (a, b) = [b, a]

        sz[a] += sz[b]; par[b] = a
        data[a].add(attachA); data[b].add(attachB)
        return true
    }
}
