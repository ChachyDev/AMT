package club.chachy.amt.handlers.storage

import com.uchuhimo.konf.Config
import java.io.File

interface StorageHandler {
    fun getMinecraftDirectory(config: Config): File

    fun getModsDirectory(config: Config): File
}