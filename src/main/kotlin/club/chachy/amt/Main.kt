package club.chachy.amt

import club.chachy.amt.commands.AMTMainCommand
import club.chachy.amt.commands.install.Install
import club.chachy.amt.handlers.repository.impl.DefaultRepositoryHandler
import com.github.ajalt.clikt.core.subcommands

fun main(args: Array<String>) {
    val repoHandler = DefaultRepositoryHandler()
    val amt = AMT().initialize {
        repositoryHandler = repoHandler
    }
    AMTMainCommand()
        .subcommands(
            Install(amt.repositoryHandler, amt.downloadHandler, amt.storageHandler)
        )
        .main(args)
}