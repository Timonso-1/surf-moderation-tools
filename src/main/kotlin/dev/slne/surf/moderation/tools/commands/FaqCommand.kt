package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentManyPlayers
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.moderation.tools.commands.argument.faqArgument
import dev.slne.surf.moderation.tools.faq.Faq
import dev.slne.surf.moderation.tools.service.faqService
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import org.bukkit.entity.Player

fun faqCommand() = commandAPICommand("faq") {
    withPermission(PermissionRegistry.COMMAND_FAQ)
    faqArgument("faq")
    entitySelectorArgumentManyPlayers("targets", allowEmpty = true, optional = true)

    playerExecutor { player, args ->
        val faq: Faq by args
        val targets: Collection<Player>? by args
        val usableTargets = targets ?: emptyList()

        faqService.sendFaq(player, faq, usableTargets)
    }
}

