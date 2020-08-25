package club.chachy.amt

import club.chachy.amt.commands.AMTMainCommand
import club.chachy.amt.commands.install.Install
import club.chachy.amt.commands.remove.Remove
import com.github.ajalt.clikt.core.subcommands

fun main(args: Array<String>) {

    val amt = AMT().initialize()
    AMTMainCommand()
        .subcommands(
            Install(amt.repositoryHandler, amt.downloadHandler, amt.storageHandler),
            Remove(amt.repositoryHandler, amt.storageHandler, amt.downloadHandler)
        )
        .main(args)
}