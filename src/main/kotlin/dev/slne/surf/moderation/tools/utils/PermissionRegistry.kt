package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.api.paper.permission.PermissionRegistry


object PermissionRegistry : PermissionRegistry() {
    private const val PREFIX = "surf.moderation.tools"
    private const val COMMAND_PREFIX = "$PREFIX.command"

    val COMMAND_ROTATE = create("$COMMAND_PREFIX.rotation")
    val COMMAND_STOP_INTERACTION = create("$COMMAND_PREFIX.stopinteraction")
    val COMMAND_FREEZE = create("$COMMAND_PREFIX.freeze")
    val COMMAND_UNFREEZE = create("$COMMAND_PREFIX.unfreeze")
    val COMMAND_FAQ = create("$COMMAND_PREFIX.faq")
    val COMMAND_PING_PLAYER = create("$COMMAND_PREFIX.pingplayer")
    val COMMAND_SURF_MOD_TOOLS = create("$COMMAND_PREFIX.surfmodtools")
}