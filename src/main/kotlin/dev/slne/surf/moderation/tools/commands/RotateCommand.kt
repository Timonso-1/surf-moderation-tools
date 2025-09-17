package dev.slne.surf.moderation.tools.commands

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun rotateCommand() = commandAPICommand("rotate") {
    playerArgument("targetPlayer")
    withPermission(ModPermissionRegistry.COMMAND_ROTATE)

    anyExecutor { sender, args ->
        val targetPlayer: Player by args

        val randomYaw = (-180..180).random().toFloat()
        val randomPitch = (-90..90).random().toFloat()

        plugin.launch(plugin.entityDispatcher(targetPlayer)) {
            targetPlayer.setRotation(randomYaw, randomPitch)

            sender.sendText {
                appendPrefix()
                success("Der Spieler ")
                variableValue(targetPlayer.name)
                success(" wurde erfolgreich rotiert.")
            }
        }
    }
}