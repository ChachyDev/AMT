package club.chachy.amt.handlers.repository.impl

import club.chachy.amt.handlers.repository.RepositoryHandler
import club.chachy.amt.handlers.repository.data.Package
import club.chachy.amt.handlers.repository.data.Repository
import club.chachy.amt.handlers.repository.data.Vendor
import club.chachy.amt.utils.repo.getPackage
import club.chachy.amt.utils.repo.hasPackage

class DefaultRepositoryHandler : RepositoryHandler {
    override fun processPackage(pkg: Package): Boolean {
        return runCatching {
            true
        }.getOrNull() ?: return false
    }

    override fun findPackage(pkg: String, vendors: List<Vendor>): Package? {
        var p: Package? = null
        for (repo in repositories) {
            if (repo.hasPackage(pkg, vendors)) {
                p = repo.getPackage(pkg, vendors)
                break
            }
        }
        return p
    }

    override fun findPackageRepository(pkg: Package): Repository? {
        var r: Repository? = null
        for (repo in repositories) {
            if (repo.hasPackage(pkg.name, pkg.vendors)) {
                r = repo
                break
            }
        }
        return r
    }
}