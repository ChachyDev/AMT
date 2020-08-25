package club.chachy.amt.handlers.storage.impl

import club.chachy.amt.handlers.storage.StorageHandler
import com.uchuhimo.konf.Config
import java.io.File

class DefaultStorageHandler : StorageHandler {
    override fun getMinecraftDirectory(config: Config) = File(config[DirectorySpec.minecraftDirectory])

    override fun getModsDirectory(config: Config) = File(getMinecraftDirectory(config), "mods")

    init {
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                defaultConfig.save()
            }
        })
    }
}