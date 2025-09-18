package dev.slne.surf.moderation.tools.commands.argument

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.slne.surf.moderation.tools.faq.Faq

import dev.slne.surf.surfapi.core.api.messages.adventure.buildText

class FaqArgument(nodeName: String) :
    CustomArgument<Faq, String>(StringArgument(nodeName), { info ->
        Faq[info.input] ?: throw CustomArgumentException.fromAdventureComponent {
            buildText {
                appendPrefix()
                error("Der Faq wurde nicht gefunden.")
            }
        }
    }) {
    init {
        replaceSuggestions(ArgumentSuggestions.stringCollection {
            Faq.all().map { it.name }
        })
    }
}

inline fun CommandAPICommand.faqArgument(
    nodeName: String,
    optional: Boolean = false,
    block: Argument<*>.() -> Unit = {}
): CommandAPICommand =
    withArguments(FaqArgument(nodeName).setOptional(optional).apply(block))