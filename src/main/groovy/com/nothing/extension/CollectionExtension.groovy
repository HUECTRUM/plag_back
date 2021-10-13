package com.nothing.extension

class CollectionExtension {
    static <T> Collection<T> flattenOnce(final Collection<T> collection) {
        return flattenOnceResursive(collection, [], 0)
    }

    private static Collection flattenOnceResursive(Iterable collection, Collection res, int depth) {
        collection.each { element ->
            if (element instanceof Collection && !depth) {
                flattenOnceResursive(element as Collection, res, depth + 1)
            } else {
                res << element
            }
        }
        return res
    }
}
