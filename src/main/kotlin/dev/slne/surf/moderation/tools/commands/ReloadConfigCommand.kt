package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import kotlin.system.measureTimeMillis

fun CommandAPICommand.surfModToolsReloadCommand() = subcommand("reload") {
    withPermission(PermissionRegistry.COMMAND_SURF_MOD_TOOLS)
    anyExecutor { sender, _ ->

        val ms = measureTimeMillis {
            plugin.moderationToolConfig.reload()
        }

        sender.sendText {
            appendPrefix()
            success("Das Plugin wurde erfolgreich neu geladen ")
            spacer("({$ms}ms)")
        }
    }
}