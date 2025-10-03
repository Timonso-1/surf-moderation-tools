package dev.slne.surf.moderation.tools.commands

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerArgument
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.service.freezeService
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import dev.slne.surf.moderation.tools.utils.durationArgument
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player


fun freezeCommand() = commandAPICommand("freeze") {
    withPermission(PermissionRegistry.COMMAND_FREEZE)
    playerArgument("targetPlayer")
    durationArgument("duration")

    anyExecutor { sender, args ->
        val targetPlayer: Player by args
        val duration: Long by args

        val targetUuid = targetPlayer.uniqueId

        if (freezeService.isFrozen(targetUuid)) {
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

        freezeService.freeze(targetUuid, duration)

        plugin.launch(plugin.entityDispatcher(targetPlayer)) {
            val location = targetPlayer.location.clone()
            val world = location.world

            var teleported = false
            for (y in location.blockY downTo 0) {
                val block = world.getBlockAt(location.blockX, y, location.blockZ)
                if (block.type.isSolid) {
                    location.y = y + 1.0
                    targetPlayer.teleportAsync(location)
                    teleported = true
                    break
                }
            }
            if (!teleported) {
                val fallbackLocation = world.spawnLocation
                targetPlayer.teleportAsync(fallbackLocation)
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