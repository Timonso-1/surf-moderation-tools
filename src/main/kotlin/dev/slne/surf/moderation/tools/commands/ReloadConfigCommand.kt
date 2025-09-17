package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun CommandAPICommand.reloadConfigCommand() = subcommand("reload") {
    withPermission(ModPermissionRegistry.COMMAND_CONFIG)
    anyExecutor { sender, args ->

        plugin.moderationToolConfig.reload()

        sender.sendText {
            appendPrefix()
            success("Die Config wurde neu geladen.")
        }
    }
}