package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickOpensUrl
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder

sealed class Faq(
    val name: String,
    val message: SurfComponentBuilder.() -> Unit
) {

    companion object {
        val ALL_FAQS = listOf(
            Whitelist,
        )

        fun getFaqByName(name: String) = ALL_FAQS.firstOrNull { it.name.equals(name, ignoreCase = true) }
    }

    object Whitelist : Faq("whitelist", {
        text("So kannst du dich whitelisten lassen: ")
        append {
            variableValue("Klicke, hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/...")
        }
    })

}