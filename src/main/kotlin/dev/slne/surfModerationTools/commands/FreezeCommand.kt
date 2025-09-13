package dev.slne.surfModerationTools.commands

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surfModerationTools.permissions.Permissions
import dev.slne.surfModerationTools.plugin
import dev.slne.surfModerationTools.utils.FreezeManager
import org.bukkit.entity.Player

fun freezeCommand() = commandAPICommand("freeze") {
    playerArgument("targetPlayer")
    withPermission(Permissions.COMMAND_FREEZE)

    anyExecutor { sender, args ->
        val targetPlayer: Player by args

        plugin.launch(plugin.entityDispatcher(targetPlayer)) {

            if (!FreezeManager.freezePlayer(targetPlayer)) {
                sender.sendText {
                    appendPrefix()
                    error("Der Spieler ")
                    variableValue(targetPlayer.name)
                    error(" ist bereits eingefroren.")
                }
                return@launch
            }

            sender.sendText {
                appendPrefix()
                success("Der Spieler ")
                variableValue(targetPlayer.name)
                success(" wurde erfolgreich eingefroren.")
            }
        }
    }
}