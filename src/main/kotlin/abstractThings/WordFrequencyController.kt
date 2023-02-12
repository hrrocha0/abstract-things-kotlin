package abstractThings

class WordFrequencyController(path: String) {
    private val storage = DataStorageManager(path)
    private val stopWordManager = StopWordManager()
    private val wordFrequencyCounter = WordFrequencyManager()

    fun run() {
        storage.words
            .filter { word -> !stopWordManager.isStopWord(word) }
            .forEach { word -> wordFrequencyCounter.incrementCount(word) }

        wordFrequencyCounter.sorted().forEach { (w, c) -> println("$w - $c") }
    }
}