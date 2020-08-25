package club.chachy.amt.handlers.storage.impl

import club.chachy.amt.handlers.repository.data.Repository
import com.uchuhimo.konf.ConfigSpec

object RepoSpec : ConfigSpec() {
    val repos by optional(mutableListOf(Repository("http://localhost:8080")))
}