package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.moderation.tools.config.configs.ArtyMessagesCooldownConfig
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry

fun CommandAPICommand.setArtyCooldownCommand() = subcommand("setArtyCooldown") {
    withPermission(ModPermissionRegistry.COMMAND_CONFIG)
    integerArgument("milliseconds")
    anyExecutor { sender, args ->
        val int: Int by args
        plugin.surfModerationToolConfig.edit {
            ArtyMessagesCooldownConfig(int)
        }
    }
}