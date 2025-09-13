package dev.slne.surfModerationTools.utils

import org.bukkit.entity.Player
import java.util.UUID

object FreezeManager {
    val frozenPlayers: MutableSet<UUID> = mutableSetOf()

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