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

        fun getFaqByName(name: String) = ALL_FAQS.firstOrNull { it.name.equals(name, ignoreCase = true) }
    }

    object Whitelist : Faq("how-to-join", {
        text("So kannst du dich whitelisten lassen:")
        appendSpace()
        append {
            variableValue("Anleitung zum Beitreten")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/how-to-join#survival")
        }
    })

    object Ticket : Faq("how-to-open-ticket", {
        text("Eine Anleitung, wie du ein Ticket erstellen kannst, findest du hier:")
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