package dev.slne.surfModerationTools.permissions

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object Permissions : PermissionRegistry() {

    private const val PREFIX = "surf-moderation-tools"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    const val COMMAND_MAIN = "$COMMAND_PREFIX.main"
    const val COMMAND_ROTATE = "$COMMAND_PREFIX.rotation"

}