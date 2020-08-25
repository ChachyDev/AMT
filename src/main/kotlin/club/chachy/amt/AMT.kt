package club.chachy.amt

import club.chachy.amt.handlers.download.DownloadHandler
import club.chachy.amt.handlers.download.impl.DefaultDownloadHandler
import club.chachy.amt.handlers.repository.RepositoryHandler
import club.chachy.amt.handlers.repository.impl.DefaultRepositoryHandler
import club.chachy.amt.handlers.storage.StorageHandler
import club.chachy.amt.handlers.storage.impl.DefaultStorageHandler

class AMT {
    var amtBuilder: AMTBuilder? = null

    fun initialize(builder: AMTBuilder.() -> Unit): AMTBuilder {
        amtBuilder = AMTBuilder().apply(builder)
        return amtBuilder ?: error("AMT Builder is null")
    }

    fun initialize(): AMTBuilder {
        amtBuilder = AMTBuilder()
        return amtBuilder ?: error("AMT Builder is null")
    }
}

class AMTBuilder {
    var downloadHandler: DownloadHandler = DefaultDownloadHandler()

    var repositoryHandler: RepositoryHandler = DefaultRepositoryHandler()

    var storageHandler: StorageHandler = DefaultStorageHandler()
}