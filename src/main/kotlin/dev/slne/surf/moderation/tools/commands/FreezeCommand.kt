package dev.slne.surf.moderation.tools.commands

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentOnePlayer
import dev.jorel.commandapi.kotlindsl.getValue
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.service.FreezeService
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import dev.slne.surf.moderation.tools.utils.durationArgument
import dev.slne.surf.surfapi.bukkit.api.command.executors.anyExecutorSuspend
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import kotlinx.coroutines.future.await
import kotlinx.coroutines.withContext
import org.bukkit.entity.Player
import kotlin.time.Duration


fun freezeCommand() = commandAPICommand("freeze") {
    withPermission(PermissionRegistry.COMMAND_FREEZE)
    entitySelectorArgumentOnePlayer("targetPlayer")
    durationArgument("duration")

    anyExecutorSuspend { sender, args ->
        val targetPlayer: Player by args
        val duration: Long by args

        val targetUuid = targetPlayer.uniqueId

        if (FreezeService.isFrozen(targetUuid)) {
            sender.sendText {
                appendInfoPrefix()
                variableValue(targetPlayer.name)
                appendSpace()
                error("ist bereits eingefroren.")
            }
            return@anyExecutorSuspend
        }

        FreezeService.freeze(targetUuid, duration)

        withContext(plugin.entityDispatcher(targetPlayer)) {
            val location = targetPlayer.location
            val world = location.world

            val teleported = targetPlayer.teleportAsync(location.toHighestLocation()).await()

            if (!teleported) {
                targetPlayer.teleportAsync(world.spawnLocation).await()
            }
        }

        sender.sendText {
            appendSuccessPrefix()
            variableValue(targetPlayer.name)
            appendSpace()
            success("wurde erfolgreich eingefroren.")
        }
    }
}