package dev.slne.surf.moderation.tools.config.configs

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class SurfModerationToolConfig(
    var artyMessagesCooldownInMil: Long = 5_000
)
