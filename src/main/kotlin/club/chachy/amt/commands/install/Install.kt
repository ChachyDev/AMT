package club.chachy.amt.commands.install

import club.chachy.amt.handlers.download.DownloadHandler
import club.chachy.amt.handlers.repository.RepositoryHandler
import club.chachy.amt.handlers.repository.data.Vendor
import club.chachy.amt.handlers.storage.StorageHandler
import club.chachy.amt.handlers.storage.impl.defaultConfig
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import java.io.File

class Install(
    private val repositoryHandler: RepositoryHandler,
    private val downloadHandler: DownloadHandler,
    private val storageHandler: StorageHandler
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

                echo("Initializing installation of ${pkg.name}...")

                val dest = File(storageHandler.getModsDirectory(defaultConfig), pkg.path.name)

                if (dest.exists()) {
                    echo("${pkg.name} is already installed."); return
                }

                downloadHandler.downloadPackage(
                    pkg,
                    repo,
                    dest
                )

                echo("Successfully installed ${pkg.name} into your mods folder.")
            }
        }
    }
}