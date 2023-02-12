package abstractThings

import java.io.File

class StopWordManager : StopWordFilter {
    private val stopWords = File("src/main/resources/stop_words.txt").readText().split(',')

    override fun isStopWord(word: String) = word in stopWords
}