package com.nothing.utils

import java.awt.Color

import static java.lang.Math.floor
import static java.lang.Math.random

class ColorUtils {
    static def generateRandomColor() {
        return new Color(floor(random() * 255) as int, floor(random() * 255) as int, floor(random() * 255) as int)
    }
}
