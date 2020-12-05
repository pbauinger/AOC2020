fun String.isNumber() = toIntOrNull() != null

fun Boolean.toInt() = if (this) 1 else 0