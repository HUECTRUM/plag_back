package com.nothing.extension

class StringExtension {
    static def discordBoldItalic(final String self) {
        return "__**$self**__"
    }

    static def generateFaceitNameLink(final String name) {
        return "[$name](https://www.faceit.com/ru/players/$name)"
    }
}
