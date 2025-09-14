package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import org.bukkit.entity.Player
import java.util.*

object FreezeManager {
    val frozenPlayers = mutableObjectSetOf<UUID>()

    fun freezePlayer(player: Player): Boolean {
        if (!isPlayerFrozen(player)) {
            frozenPlayers.add(player.uniqueId)
            return true
        }
        return false
    }

    fun unfreezePlayer(player: Player): Boolean {
        if (isPlayerFrozen(player)) {
            frozenPlayers.remove(player.uniqueId)
            return true
        }
        return false
    }

    fun isPlayerFrozen(player: Player): Boolean {
        return player.uniqueId in frozenPlayers
    }
}