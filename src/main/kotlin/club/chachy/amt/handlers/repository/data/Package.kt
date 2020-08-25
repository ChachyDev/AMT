package club.chachy.amt.handlers.repository.data

import java.io.File

class Package(
    val name: String,
    val vendors: List<Vendor>,
    val version: String,
    val dependencies: List<Dependency>,
    val path: File,
)