package abstractThings

import java.io.File

class DataStorageManager(path: String) : DataStorage {
    private val pattern = "[\\W_]+".toRegex()
    private val file = File(path)

    override val words = file.readText().replace(pattern, " ").lowercase().split(" ")
}