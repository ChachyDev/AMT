package club.chachy.amt.handlers.repository

import club.chachy.amt.handlers.repository.data.Package
import club.chachy.amt.handlers.repository.data.Repository
import club.chachy.amt.handlers.repository.data.Vendor
import club.chachy.amt.handlers.storage.impl.RepoSpec
import club.chachy.amt.handlers.storage.impl.defaultConfig

/**
 * Used for handling implementations of repositories.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

interface RepositoryHandler {
    val repositories: MutableList<Repository>
        get() = repos // Hardcoded repo will be removed soon.

    /**
     * Used to process packages
     *
     * @param pkg Package to be processed
     *
     * @author ChachyDev
     * @since 0.1-DEV
     */
    fun processPackage(pkg: Package): Boolean

    /**
     * Implementation to process many packages
     *
     * @param pkgs List of packages to be processed.
     *
     * @author ChachyDev
     * @since 0.1-DEV
     */

    fun processPackages(pkgs: List<Package>) = pkgs.forEach { processPackage(it) }

    fun findPackage(pkg: String, vendors: List<Vendor> = emptyList()): Package?

    fun findPackageRepository(pkg: Package): Repository?

    fun addRepository(repository: Repository) = repositories.add(repository)

    fun removeRepository(repository: Repository) = repositories.remove(repository)

    companion object {
        private val repos = defaultConfig[RepoSpec.repos]
    }
}