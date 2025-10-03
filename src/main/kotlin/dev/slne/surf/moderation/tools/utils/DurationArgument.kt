package dev.slne.surf.moderation.tools.utils

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.StringArgument

object DurationArgument {

    fun durationArgument(arg: String): Argument<Long> {
        return CustomArgument(StringArgument(arg)) { argument ->
            parseRangeToMillis(argument.input)
                ?: throw CustomArgument.CustomArgumentException.fromMessageBuilder(
                    CustomArgument.MessageBuilder("Invalid duration format. Use s, m, h, d, w.")
                )
        }.replaceSuggestions(ArgumentSuggestions.strings { _ ->
            arrayOf("s", "m", "h", "d", "w")
        })
    }

    private val regex = Regex("^(\\d+)([smhdwp])\$")

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
}