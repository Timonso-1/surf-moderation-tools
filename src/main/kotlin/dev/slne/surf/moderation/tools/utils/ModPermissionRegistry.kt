package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object ModPermissionRegistry : PermissionRegistry() {

    private const val PREFIX = "surf.moderation.tools"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    val COMMAND_ROTATE = create("$COMMAND_PREFIX.rotation")
    val COMMAND_FREEZE = create("$COMMAND_PREFIX.freeze")
    val COMMAND_UNFREEZE = create("$COMMAND_PREFIX.unfreeze")
    val COMMAND_FAQ = create("$COMMAND_PREFIX.faq")

}