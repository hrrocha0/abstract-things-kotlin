package abstractThings

// Keeps the word frequency data
interface WordFrequencyCounter {
    // Increments the count for the given word
    fun incrementCount(word: String)

    // Returns the words and their frequencies, sorted by frequency
    fun sorted(): Map<String, Int>
}