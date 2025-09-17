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
    longArgument("millis")
    anyExecutor { sender, args ->
        val millis: Long by args
        plugin.surfModerationToolConfig.edit {
            artyMessagesCooldownConfig.artyMessagesCooldownInMil = millis
        }

        sender.sendText {
            appendPrefix()
            success("Der Artillery Nachrichten Cooldown wurde auf")
            appendSpace()
            variableValue("$millis")
            appendSpace()
            success("ms gesetzt.")
        }
    }
}