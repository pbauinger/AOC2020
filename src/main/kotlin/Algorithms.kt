import java.lang.Integer.max

//endIdx: exclusive
fun twoSum(numbers: List<Long>, startIdx: Int, endIdx: Int, target: Long): Long? {
    val seen = mutableSetOf<Long>()
    for (i in startIdx until max(numbers.size, endIdx)) {
        val num = numbers[i]
        if (seen.contains(num)) return num * (target - num);
        seen.add(target - num)
    }
    return null
}