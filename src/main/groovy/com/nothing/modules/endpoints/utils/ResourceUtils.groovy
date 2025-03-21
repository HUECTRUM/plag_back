package com.nothing.modules.endpoints.utils

class ResourceUtils {
    static def resource(String path) {
        return new File(ResourceUtils.class
                .getClassLoader()
                .getResource(path)
                .getFile()
        )
    }
}
