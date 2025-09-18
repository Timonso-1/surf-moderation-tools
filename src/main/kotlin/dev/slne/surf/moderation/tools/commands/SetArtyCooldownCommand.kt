package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.longArgument
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun CommandAPICommand.setMessageCooldownCommand() = subcommand("setMessageCooldown") {
    withPermission(PermissionRegistry.COMMAND_SURF_MOD_TOOLS)
    longArgument("millis")
    anyExecutor { sender, args ->
        val millis: Long by args

        plugin.moderationToolConfig.edit {
            faqCooldown = millis
        }

        sender.sendText {
            appendPrefix()
            success("Der Nachrichten Cooldown wurde auf")
            appendSpace()
            variableValue("$millis")
            appendSpace()
            success("ms gesetzt.")
        }
    }
}