package club.chachy.amt.handlers.storage.impl

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.Spec
import com.uchuhimo.konf.source.json.toJson
import java.io.File

val defaultConfig = Config {
    addSpecs(DirectorySpec, RepoSpec)
    if (!getOSConfigFile().exists()) {
        getOSConfigFolder().mkdirs()
        getOSConfigFile().createNewFile()
        toJson.toFile(getOSConfigFile())
    }
}.from.json.file(getOSConfigFile())

fun Config.addSpecs(vararg specs: Spec) = specs.forEach { addSpec(it) }

private fun getOSConfigFolder() = with(System.getProperty("os.name")) {
    when {
        startsWith("Windows") -> File(System.getenv("APPDATA"), ".amt")
        else -> File(File(System.getProperty("user.home"), ".config"), ".amt")
    }
}

private fun getOSConfigFile() = File(getOSConfigFolder(), "config.json")

fun Config.save() = toJson.toFile(getOSConfigFile())