package club.chachy.amt.handlers.storage.impl

import com.uchuhimo.konf.ConfigSpec
import java.io.File

object DirectorySpec : ConfigSpec() {
    val minecraftDirectory by optional(getDefaultMinecraftDirectory().absolutePath)

    private fun getDefaultMinecraftDirectory(): File {
        return with(System.getProperty("os.name")) {
            when {
                startsWith("Windows") -> File(System.getenv("APPDATA"), ".minecraft")
                startsWith("Mac") -> File(File(System.getProperty("user.home"), "Application Support"), "minecraft")
                else -> File(System.getProperty("user.home"), ".minecraft")
            }
        }
    }
}