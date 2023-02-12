package abstractThings

// Models the stop word filter
interface StopWordFilter {
    // Checks whether the given word is a stop word
    fun isStopWord(word: String): Boolean
}