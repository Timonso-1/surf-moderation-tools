package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun CommandAPICommand.setArtyCooldownCommand() = subcommand("setArtyCooldown") {
    withPermission(ModPermissionRegistry.COMMAND_CONFIG)
    integerArgument("milliseconds")
    anyExecutor { sender, args ->
        val milliseconds: Int by args
        plugin.surfModerationToolConfig.edit {
            artyMessagesCooldownConfig.artyMessagesCooldownInMil = milliseconds
        }

        sender.sendText {
            appendPrefix()
            success("Der Artillery Nachrichten Cooldown wurde auf")
            appendSpace()
            variableValue("$milliseconds")
            appendSpace()
            success("ms gesetzt.")
        }
    }
}