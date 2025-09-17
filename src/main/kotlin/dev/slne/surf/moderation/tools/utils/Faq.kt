package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickOpensUrl
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder
import net.kyori.adventure.text.format.TextDecoration

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
        text("Informationen, wie du dich whitelisten lassen kannst, findest du hier.")
        appendSpace()
        append {
            text("Für Events ")
            decorate(TextDecoration.BOLD)
        }
        text("benötigst du jedoch")
        appendSpace()
        append {
            text("keine Whitelist")
            decorate(TextDecoration.BOLD)
        }
        text(", sondern nur für den Survival-Server:")
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