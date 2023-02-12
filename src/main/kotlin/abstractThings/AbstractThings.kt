package abstractThings

import java.io.File

interface DataStorage {
    val words: List<String>
}

interface StopWordFilter {
    fun isStopWord(word: String): Boolean
}

interface WordFrequencyCounter {
    fun incrementCount(word: String)
    fun sorted(): Map<String, Int>
}

class DataStorageManager(path: String) : DataStorage {
    private val file = File(path)
    private val pattern = "[\\W_]+".toRegex()

    override val words = file
        .readText()
        .replace(pattern, " ")
        .lowercase()
        .split(" ")
        .filter(String::isNotBlank)
}

class StopWordManager : StopWordFilter {
    private val stopWords = File("src/main/resources/stop_words.txt").readText().split(',')

    override fun isStopWord(word: String) = word in stopWords
}

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

class WordFrequencyController(path: String) {
    private val storage = DataStorageManager(path)
    private val stopWordFilter = StopWordManager()
    private val wordFrequencyCounter = WordFrequencyManager()

    fun run() {
        storage.words
            .filter { word -> !stopWordFilter.isStopWord(word) }
            .forEach { word -> wordFrequencyCounter.incrementCount(word) }

        wordFrequencyCounter.sorted().forEach { (w, c) -> println("$w - $c") }
    }
}

fun main(args: Array<String>) = WordFrequencyController(args[0]).run()