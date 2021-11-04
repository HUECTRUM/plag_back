package com.nothing.utils

class ResourceUtils {
    static def getResourceFile(String path) {
        return new File(ResourceUtils.class
                .getClassLoader()
                .getResource(path)
                .getFile()
        )
    }
}
