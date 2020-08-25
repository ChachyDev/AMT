package club.chachy.amt.handlers.download

import club.chachy.amt.handlers.repository.data.Package
import club.chachy.amt.handlers.repository.data.Repository
import java.io.File

/**
 * An interface for modular implementation's of downloading mods
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

interface DownloadHandler {
    /**
     * Used for downloading packages
     *
     * @author ChachyDev
     * @since 0.1-DEV
     */

    fun downloadPackage(pkg: Package, repository: Repository, dest: File): Boolean
}