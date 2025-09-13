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
import kotlinx.coroutines.withContext

import org.bukkit.entity.Player


fun rotateCommand() = commandAPICommand("playerRotate") {
    playerArgument("player")
    withPermission(Permissions.COMMAND_ROTATE)

    anyExecutor { sender, args ->
        val targetPlayer: Player by args

        val targetLocation = targetPlayer.location
        val randomYaw = (-180..180).random().toFloat()
        val randomPitch = (-90..90).random().toFloat()

        plugin.launch {
            withContext(plugin.entityDispatcher(targetPlayer)) {
                targetLocation.setRotation(randomYaw, randomPitch)
                targetPlayer.teleport(targetLocation)
            }

            sender.sendText {
                appendPrefix()
                success("Der Spieler ")
                variableValue(targetPlayer.name)
                success(" wurde erfolgreich rotiert.")
            }
        }
    }
}