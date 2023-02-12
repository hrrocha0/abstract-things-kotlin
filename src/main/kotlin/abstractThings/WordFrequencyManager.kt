package abstractThings

class WordFrequencyManager : WordFrequencyCounter {
    private val wordFrequencies = mutableMapOf<String, Int>()
    private val comparator = compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first }

    override fun incrementCount(word: String) = if (word in wordFrequencies) {
        wordFrequencies[word] = wordFrequencies[word]!! + 1
    } else {
        wordFrequencies[word] = 1
    }

    override fun sorted() = wordFrequencies.toList().sortedWith(comparator).toMap()
}