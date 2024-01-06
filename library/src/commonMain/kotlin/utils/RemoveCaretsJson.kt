package utils

fun String.removeCarets() =
    this.substring(1..<this.length - 1)
