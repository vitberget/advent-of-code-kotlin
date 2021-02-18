package se.vbgt.aoc.year2020.day7.part2

fun day7part2(mapOfBags: Map<String, Map<String, Int>>) {
    val targetBagName = "shiny gold"
    val count = bagsInBag(mapOfBags, targetBagName)

    println("Day7 part2: ${count}")
}

fun bagsInBag(mapOfBags: Map<String, Map<String, Int>>, bagName: String): Int {
    val bag = mapOfBags[bagName]
    return if (bag == null || bag.keys.isEmpty()) {
        0
    } else {
        bag.map { (bagsInBag(mapOfBags, it.key) + 1) * it.value }
            .sum()
    }
}