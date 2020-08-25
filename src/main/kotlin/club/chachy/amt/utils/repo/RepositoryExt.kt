package club.chachy.amt.utils.repo

import club.chachy.amt.handlers.repository.data.Package
import club.chachy.amt.handlers.repository.data.Repository
import club.chachy.amt.handlers.repository.data.Vendor
import club.chachy.amt.utils.http.http
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

private data class SearchResponse(val success: Boolean)

fun Repository.hasPackage(pkg: String, vendors: List<Vendor>): Boolean {
    return runBlocking {
        http.get<SearchResponse>("$url/search/mod/$pkg") {
            vendors.takeIf { it.isNotEmpty() }
                ?.let { parameter("vendors", it.joinToString { vendor -> vendor.name }) }
        }.success
    }
}

fun Repository.getPackage(pkg: String, vendors: List<Vendor>): Package? {
    return runBlocking {
        runCatching {
            http.get<Package>("$url/mod/download/info/${pkg}") {
                vendors.takeIf { it.isNotEmpty() }
                    ?.let { parameter("vendors", it.joinToString { vendor -> vendor.name }) }
            }
        }.getOrNull()
    }
}