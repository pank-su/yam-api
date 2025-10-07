package su.pank.yamapi.utils

/**
 * Удаляет кавычки из строки.
 *
 * @return Строка без кавычек.
 */
fun String.removeCarets() = this.substring(1..<this.length - 1)
