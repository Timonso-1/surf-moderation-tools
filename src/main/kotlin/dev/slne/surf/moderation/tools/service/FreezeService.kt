package dev.slne.surf.moderation.tools.service

import dev.slne.surf.surfapi.core.api.util.freeze
import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import java.util.UUID

class FreezeService {
    private val frozenPlayers = mutableObjectSetOf<UUID>()

    fun freeze(uuid: UUID) = frozenPlayers.add(uuid)
    fun unfreeze(uuid: UUID) = frozenPlayers.remove(uuid)
    fun isFrozen(uuid: UUID) = frozenPlayers.contains(uuid)

    fun frozenPlayers() = frozenPlayers.freeze()

    companion object {
        val INSTANCE = FreezeService()
    }
}


val freezeService get() = FreezeService.INSTANCE