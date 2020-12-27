import java.math.BigInteger

fun String.isNumber() = toIntOrNull() != null

fun Boolean.toInt() = if (this) 1 else 0

fun Long.pow(exp: Int) = BigInteger.valueOf(this).pow(exp).toLong()