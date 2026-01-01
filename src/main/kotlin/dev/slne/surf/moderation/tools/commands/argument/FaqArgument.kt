package dev.slne.surf.moderation.tools.commands.argument

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.slne.surf.moderation.tools.faq.Faq

class FaqArgument(nodeName: String) : CustomArgument<Faq, String>(
    StringArgument(nodeName),
    { info ->
        Faq.byId(info.input) ?: throw CustomArgumentException.fromMessageBuilder(
            MessageBuilder()
                .append("Faq ")
                .appendArgInput()
                .append(" not found.")
        )
    }
) {
    init {
        replaceSuggestions(ArgumentSuggestions.strings(Faq.entries.map { it.id }))
    }
}

inline fun CommandAPICommand.faqArgument(
    nodeName: String,
    optional: Boolean = false,
    block: Argument<*>.() -> Unit = {}
): CommandAPICommand = withArguments(FaqArgument(nodeName).setOptional(optional).apply(block))