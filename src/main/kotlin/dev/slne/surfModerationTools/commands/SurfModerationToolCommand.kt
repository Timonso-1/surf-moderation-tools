package dev.slne.surfModerationTools.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surfModerationTools.commands.subcommands.rotateCommand
import dev.slne.surfModerationTools.permissions.Permissions

suspend fun surfModerationToolCommand() = commandAPICommand("surfModTool") {
        withPermission(Permissions.COMMAND_MAIN)
        rotateCommand()
}