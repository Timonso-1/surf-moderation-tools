package dev.slne.surf.moderation.tools.dialog

import dev.slne.surf.api.paper.dialog.*
import net.kyori.adventure.text.minimessage.MiniMessage.miniMessage

fun createFaqPreviewDialog(faqContent: String) = dialog {
    base {
        title { primary("FAQ Vorschau") }
        body {
            plainMessage {
                append(miniMessage().deserialize(faqContent))
            }
        }
    }
    type {
        notice {
            label { text("Ok") }
            action {
                playerCallback { player ->
                    player.showDialog(createFaqDialog())
                }
            }
        }
    }
}

