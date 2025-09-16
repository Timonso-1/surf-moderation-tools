package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Bukkit.reload

fun CommandAPICommand.reloadConfigCommand() = subcommand("reload") {
    withPermission(ModPermissionRegistry.COMMAND_CONFIG)
    anyExecutor { sender, args ->
        reload()
        sender.sendText {
            appendPrefix()
            success("Die Config wurde neu geladen.")
        }
    }
}