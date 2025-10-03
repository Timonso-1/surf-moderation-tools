package dev.slne.surf.moderation.tools.utils

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText


class DurationArgument(arg: String) : CustomArgument<Long, String>(
    StringArgument(arg),
    { argument ->
        parseRangeToMillis(argument.input)
            ?: throw CustomArgumentException.fromAdventureComponent {
                buildText {
                    appendPrefix()
                    error("Nutze das Format <Zahl><Einheit> (s, m, h, d, w) für die Dauer.")
                }
            }
    }
) {
    init {
        replaceSuggestions(ArgumentSuggestions.strings { _ ->
            arrayOf("s", "m", "h", "d", "w")
        })
    }
}

inline fun CommandAPICommand.durationArgument(
    nodeName: String,
    optional: Boolean = false,
    block: Argument<*>.() -> Unit = {}
): CommandAPICommand =
    withArguments(DurationArgument(nodeName).setOptional(optional).apply(block))

private val regex = Regex("^(\\d+)([smhdw])\$")

private fun parseRangeToMillis(input: String): Long? {
    val match = regex.matchEntire(input.trim()) ?: return null

    val (valueStr, unit) = match.destructured
    val value = valueStr.toLongOrNull() ?: return null

    return when (unit.lowercase()) {
        "s" -> value * 1000
        "m" -> value * 60 * 1000
        "h" -> value * 60 * 60 * 1000
        "d" -> value * 24 * 60 * 60 * 1000
        "w" -> value * 7 * 24 * 60 * 60 * 1000
        else -> null
    }
}
