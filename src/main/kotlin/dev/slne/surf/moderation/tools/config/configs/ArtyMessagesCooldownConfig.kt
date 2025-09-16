package dev.slne.surf.moderation.tools.config.configs

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class ArtyMessagesCooldownConfig(
    var artyMessagesCooldownInMil: Int = 5000
)
