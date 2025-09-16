package dev.slne.surf.moderation.tools.config.configs

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class ArtyMessagesCooldownConfig(
    val artyMessagesCooldownInMil: Int = 5000
)
