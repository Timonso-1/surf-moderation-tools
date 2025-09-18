package dev.slne.surf.moderation.tools.config

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class SurfModerationToolConfig(
    var faqCooldown: Long = 5_000
)