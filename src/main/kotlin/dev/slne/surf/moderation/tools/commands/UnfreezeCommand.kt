package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.slne.surf.moderation.tools.service.freezeService
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun unfreezeCommand() = commandAPICommand("unfreeze") {
    playerArgument("targetPlayer")
    withPermission(PermissionRegistry.COMMAND_UNFREEZE)

    anyExecutor { sender, args ->
        val targetPlayer: Player by args

        if (!freezeService.isFrozen(targetPlayer.uniqueId)) {
            sender.sendText {
                appendPrefix()
                error("Der Spieler ")
                variableValue(targetPlayer.name)
                error(" ist nicht eingefroren.")
            }
            return@anyExecutor
        }

        sender.sendText {
            appendPrefix()
            success("Der Spieler ")
            variableValue(targetPlayer.name)
            success(" wurde erfolgreich aufgetaut.")
        }
    }
}