package club.chachy.amt.commands.remove

import club.chachy.amt.handlers.download.DownloadHandler
import club.chachy.amt.handlers.repository.RepositoryHandler
import club.chachy.amt.handlers.repository.data.Vendor
import club.chachy.amt.handlers.storage.StorageHandler
import club.chachy.amt.handlers.storage.impl.defaultConfig
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import java.io.File

class Remove(
    private val repositoryHandler: RepositoryHandler,
    private val storageHandler: StorageHandler,
    private val downloadHandler: DownloadHandler
) : CliktCommand() {
    private val packages by argument().multiple(true)

    override fun run() {
        val splitPackage = packages.map { it.split("/") }

        val packages = runCatching {
            splitPackage.map { it.component1() to it.component2() }
        }.getOrNull()

        if (packages == null) {
            echo("Please make sure you use the correct format (vendor/package)"); return
        }

        packages.forEach {
            val vendor = Vendor(it.first, null, null)
            val pkg = repositoryHandler.findPackage(it.second, listOf(vendor))
            if (pkg == null) {
                echo("Could not find package ${it.second}"); return
            } else {
                val repo = repositoryHandler.findPackageRepository(pkg)

                if (repo == null) {
                    echo("Could not find repo for package"); return
                }

                echo("Initializing uninstallation of ${pkg.name}...")

                val dest = File(storageHandler.getModsDirectory(defaultConfig), pkg.path.name)

                if (!dest.exists()) {
                    echo("${pkg.name} is already uninstalled."); return
                }

                dest.delete()

                echo("Successfully uninstalled ${pkg.name} from your mods folder.")
            }
        }
    }
}