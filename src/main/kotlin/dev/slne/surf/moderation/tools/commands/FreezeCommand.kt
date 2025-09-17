package dev.slne.surf.moderation.tools.commands

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.FreezeManager
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.Material
import org.bukkit.entity.Player

fun freezeCommand() = commandAPICommand("freeze") {
    playerArgument("targetPlayer")
    withPermission(ModPermissionRegistry.COMMAND_FREEZE)

    anyExecutor { sender, args ->
        val targetPlayer: Player by args

        if (!FreezeManager.freezePlayer(targetPlayer)) {
            sender.sendText {
                appendPrefix()
                error("Der Spieler")
                appendSpace()
                variableValue(targetPlayer.name)
                appendSpace()
                error("ist bereits eingefroren.")
            }
            return@anyExecutor
        }

        plugin.launch(plugin.entityDispatcher(targetPlayer)) {
            if (targetPlayer.location.block.type == Material.AIR) {
                val location = targetPlayer.location.clone()
                location.y = targetPlayer.world.getHighestBlockYAt(location).toDouble()
                location.y++
                targetPlayer.teleportAsync(location)
            }
        }

        sender.sendText {
            appendPrefix()
            success("Der Spieler")
            appendSpace()
            variableValue(targetPlayer.name)
            appendSpace()
            success("wurde erfolgreich eingefroren.")
        }

    }
}