package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentManyPlayers
import dev.jorel.commandapi.kotlindsl.getValue
import dev.slne.surf.api.paper.command.executors.playerExecutorSuspend
import dev.slne.surf.moderation.tools.commands.argument.faqArgument
import dev.slne.surf.moderation.tools.faq.Faq
import dev.slne.surf.moderation.tools.service.FaqService
import dev.slne.surf.moderation.tools.utils.PermissionRegistry
import org.bukkit.entity.Player

fun faqCommand() = commandAPICommand("faq") {
    withPermission(PermissionRegistry.COMMAND_FAQ)
    faqArgument("faq")
    entitySelectorArgumentManyPlayers("targets", optional = true)

    playerExecutorSuspend { player, args ->
        val faq: Faq by args
        val targets: Collection<Player>? by args

        FaqService.sendFaq(player, faq, targets)
    }
}

