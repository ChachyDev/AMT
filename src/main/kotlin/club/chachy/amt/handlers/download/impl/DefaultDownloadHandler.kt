package club.chachy.amt.handlers.download.impl

import club.chachy.amt.handlers.download.DownloadHandler
import club.chachy.amt.handlers.download.impl.exceptions.ModAlreadyInstalledException
import club.chachy.amt.handlers.repository.data.Package
import club.chachy.amt.handlers.repository.data.Repository
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.URL

class DefaultDownloadHandler : DownloadHandler {
    override fun downloadPackage(pkg: Package, repository: Repository, dest: File): Boolean {
        return try {
            if (dest.exists()) {
                throw ModAlreadyInstalledException(pkg.name)
            }
            FileUtils.copyURLToFile(createDownloadLink(pkg, repository), dest)
            true
        } catch (e: Throwable) {
            false
        }
    }

    private fun createDownloadLink(pkg: Package, repository: Repository) =
        URL(repository.url + "/mod/download/info/${pkg.name}?vendors=${pkg.vendors.joinToString { it.name }}")
}