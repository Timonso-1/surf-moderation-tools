package dev.slne.surf.moderation.tools.commands

import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.moderation.tools.utils.Faq
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.moderation.tools.utils.appendArtyPrefix
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.Player

fun faqCommand() = commandAPICommand("faq") {

    // TODO: Caffeine for duplicate FAQ requests in short time

    withPermission(ModPermissionRegistry.COMMAND_FAQ)
    multiLiteralArgument("faq", *Faq.ALL_FAQS.map { it.name }.toTypedArray())
    entitySelectorArgumentManyPlayers("targets", allowEmpty = true, optional = true)

    playerExecutor { player, args ->
        val faq: String by args
        val targets: Collection<Player>? by args
        val usableTargets = targets ?: emptyList()

        val faqEntry = Faq.getFaqByName(faq) ?: run {
            player.sendText {
                appendPrefix()

                error("Es wurde kein FAQ-Eintrag mit dem Namen ")
                variableValue(faq)
                error(" gefunden.")
            }

            return@playerExecutor
        }

        sendFaqMessage(faqEntry, usableTargets)
    }
}

private fun sendFaqMessage(faq: Faq, targets: Collection<Player> = emptyList()) {
    if (targets.isNotEmpty()) {
        targets.forEach { target ->
            target.sendText {
                appendArtyPrefix()
                append {
                    append(target.displayName())
                    decorate(TextDecoration.BOLD)
                }
                appendSpace()
                append(faq.message)
            }
        }

        return
    }

    server.sendText {
        appendArtyPrefix()
        append(faq.message)
    }
}