package dev.slne.surf.moderation.tools.service

import com.github.benmanes.caffeine.cache.Caffeine
import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import java.util.*

class FreezeService {
    private val frozenPlayers = mutableObjectSetOf<UUID>()
    private val freezeTimer = Caffeine.newBuilder()
        .build<UUID, Long>()

    fun freeze(uuid: UUID, duration: Long) {
        frozenPlayers.add(uuid)
        freezeTimer.put(uuid, System.currentTimeMillis() + duration)
    }

    fun unfreeze(uuid: UUID) {
        frozenPlayers.remove(uuid)
        freezeTimer.invalidate(uuid)
    }

    fun isFrozen(uuid: UUID): Boolean {
        val isFrozen = frozenPlayers.contains(uuid)
        if (isFrozen) {
            if ((freezeTimer.getIfPresent(uuid) ?: 0) < System.currentTimeMillis()) {
                unfreeze(uuid)
                return false
            }
        }
        return isFrozen
    }

    companion object {
        val INSTANCE = FreezeService()
    }
}


val freezeService get() = FreezeService.INSTANCE