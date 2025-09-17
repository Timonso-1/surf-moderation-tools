package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickOpensUrl
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder

sealed class Faq(
    val name: String,
    val message: SurfComponentBuilder.() -> Unit
) {

    companion object {
        fun all() = listOf(
            Whitelist,
            Ticket,
            Rules,
        )

        fun getFaqByName(name: String) = all().firstOrNull { it.name.equals(name, ignoreCase = true) }
    }

    object Whitelist : Faq("how-to-join", {
        text("Informationen, wie du dich whitelisten lassen kannst, findest du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/how-to-join#survival")
        }
        text(". Für Events benötigst du jedoch keine Whitelist, sondern nur für den Survival-Server.")

    })

    object Ticket : Faq("how-to-open-ticket", {
        text("Eine Anleitung zum Erstellen eines Tickets findest du hier:")
        appendSpace()
        append {
            variableValue("Anleitung")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/support")
        }
    })

    object Rules : Faq("rulebook", {
        text("Alle Regeln, die auf dem Server gelten, findest du hier:")
        appendSpace()
        append {
            variableValue("Regelwerk")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/rules")
        }
    })
}