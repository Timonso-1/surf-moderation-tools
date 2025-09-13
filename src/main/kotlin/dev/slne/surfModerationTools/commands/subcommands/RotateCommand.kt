package dev.slne.surfModerationTools.commands.subcommands

import com.github.shynixn.mccoroutine.folia.launch
import com.github.shynixn.mccoroutine.folia.regionDispatcher
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surfModerationTools.permissions.Permissions
import dev.slne.surfModerationTools.plugin
import kotlinx.coroutines.withContext

suspend fun CommandAPICommand.rotateCommand() = subcommand("playerRotate") {
    withArguments(PlayerArgument("player"))
    withPermission(Permissions.COMMAND_ROTATE)

    anyExecutor { sender, args ->
        val targetPlayer = args[0] as org.bukkit.OfflinePlayer
        plugin.launch {

            if (!targetPlayer.isOnline) {
                sender.sendText {
                    appendPrefix()
                    error("Der Spieler wurde nicht gefunden.")
                }
                return@launch
            }

            val targetLocation = targetPlayer.player?.location
            val randomZFloat = (-180..180).random().toFloat()
            val randomYFloat = (-90..90).random().toFloat()

            if (targetLocation != null) {
                withContext(plugin.regionDispatcher(targetLocation)) {
                    targetLocation.setRotation(randomZFloat, randomYFloat)
                    targetPlayer.player?.teleport(targetLocation)
                }

                sender.sendText {
                    appendPrefix()
                    success("Der Spieler wurde rotiert!")
                }
            }
        }
    }
}