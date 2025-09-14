package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.slne.surf.moderation.tools.utils.FreezeManager
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun freezeCommand() = commandAPICommand("freeze") {
    playerArgument("targetPlayer")
    withPermission(ModPermissionRegistry.COMMAND_FREEZE)

    anyExecutor { sender, args ->
        val targetPlayer: Player by args

        if (!FreezeManager.freezePlayer(targetPlayer)) {
            sender.sendText {
                appendPrefix()
                error("Der Spieler ")
                variableValue(targetPlayer.name)
                error(" ist bereits eingefroren.")
            }
            return@anyExecutor
        }

        sender.sendText {
            appendPrefix()
            success("Der Spieler ")
            variableValue(targetPlayer.name)
            success(" wurde erfolgreich eingefroren.")
        }

    }
}