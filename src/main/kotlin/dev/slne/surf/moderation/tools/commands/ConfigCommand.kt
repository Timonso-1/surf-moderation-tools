package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry

fun configCommand() = commandAPICommand("surfmodtools") {
    withPermission(ModPermissionRegistry.COMMAND_CONFIG)
    setArtyCooldownCommand()
    reloadConfigCommand()
}