package dev.slne.surf.moderation.tools.faq

import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import dev.slne.surf.surfapi.core.api.messages.adventure.clickOpensUrl
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder

sealed class Faq(
    val name: String,
    val message: SurfComponentBuilder.() -> Unit
) {
    companion object {
        fun all() = listOf(
            Ask,  
            BenefitsAsVeteran,  
            ClanInformation,  
            HowToCreatePlot,  
            HowToInstallVoiceChat,  
            HowToJoin,  
            HowToOpenTicket,  
            NextEvent,  
            ReadTheDocs,  
            ReportBug,  
            ReportPlayer,  
            Rulebook,  
            ServerModpack,  
            SurvivalDowntime,  
            TakePartInEvent,  
            WhyNoElytraInTheEnd,  
            WhyNoTeleportation,  
            WhyNoVillagers  
        )

        private fun byName(name: String) =
            all().firstOrNull { it.name.equals(name, ignoreCase = true) }

        operator fun get(name: String) = byName(name)
    }

    object HowToJoin : Faq("how-to-join", {
        text("Informationen, z.B. wie du dich whitelisten lassen kannst, findest du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/how-to-join#survival")
        }
        text(". Für Events benötigst du jedoch keine Whitelist, nur für den Survival-Server. ")
    })

    object HowToOpenTicket : Faq("how-to-open-ticket", {
        text("Eine Anleitung zum Erstellen eines Tickets findest du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/support")
        }
        text(".")
    })

    object Rulebook : Faq("rulebook", {
        text("Alle Regeln des Servers findest du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/rules")
        }
        text(".")
    })

    object ReadTheDocs : Faq("read-the-docs", {
        text("Diese und weitere Fragen werden in unserer ausführlichen")
        appendSpace()
        append {
            variableValue("Dokumentation")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/community-server-landing-page")
        }
        text(" beantwortet.")
    })

    object SurvivalDowntime : Faq("survival-downtime", {
        text("Der 1.21 Survival Server ist zu Ende! Wann der neue Survival Server startet, steht noch in den Sternen aber wir arbeiten bereits daran! Alle Updates & Ankündigungen dazu findest du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf den Discord zu gelangen.")
            })
            clickOpensUrl("https://discord.com/channels/133198459531558912/980810495877607524")
        }
        text(".")
    })

    object NextEvent : Faq("next-event", {
        text("Wenn ein Event geplant ist, wird dies im")
        appendSpace()
        append {
            variableValue("Discord")
            hoverEvent(buildText {
                spacer("Klicke, um auf den Discord zu gelangen.")
            })
            clickOpensUrl("https://discord.com/channels/133198459531558912/980810495877607524")
        }
        text(" oder durch CastCrafter im ")
        appendSpace()
        append {
            variableValue("Stream")
            hoverEvent(buildText {
                spacer("Klicke, um auf den Twitch-Kanal zu gelangen.")
            })
            clickOpensUrl("https://www.twitch.tv/castcrafter")
        }
        text(" angekündigt.")
    })

    object TakePartInEvent : Faq("take-part-in-event", {
        text("Wie du an einem Event teilnehmen kannst, erfährst du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/how-to-take-part-in-an-event")
        }
        text(".")
    })

    object ServerModpack : Faq("server-modpack", {
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um auf die Downloadseite zu gelangen.")
            })
            clickOpensUrl("https://modrinth.com/modpack/castcrafter-survival-server")
        }
        text(" kannst du das Server Modpack herunterladen.")
    })

    object Ask : Faq("ask", {
        text("Wenn du eine Frage hast, stell sie einfach. Such nicht nach bestimmten Leuten und frag nicht erst, ob du fragen darfst oder ob jemand da ist. Frag einfach und gedulde dich auf eine Antwort.")
    })

    object ClanInformation : Faq("clan-info", {
        text("Du möchtest über einen Clan etwas wissen?")
        appendSpace()
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/clan-more-info")
        }
        text(" findest du eine Übersicht mit zusätzlichen Informationen.")
    })

    object HowToCreatePlot : Faq("how-to-create-plot", {
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/how-to-create-a-plot")
        }
        text(" erfährst du, wie du Grundstücke erstellen kannst.")
        })

    object WhyNoVillagers : Faq("why-no-villagers", {
        text("Du fragst dich warum es auf dem Server keine Dorfbewohner gibt?")
        appendSpace()
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/specials#no-villagers")
        }
        text(" findest du die Antwort.")
    })

    object HowToInstallVoiceChat : Faq("how-to-install-voice-chat", {
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/faq.html#install-voicechat")
        }
        text(" erfährst du, wie du den Voice Chat installieren kannst.")
    })

    object ReportPlayer : Faq("report-player", {
        text("Du möchtest einen Spieler reporten?")
        appendSpace()
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/support#report-ticket")
        }
        text(" zeigen wir dir, wie du das machen kannst.")
    })

    object ReportBug : Faq("report-bug", {
        text("Du hast einen Bug gefunden?")
        appendSpace()
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/support#bugreport-ticket")
        }
        text(" erfährst du wie du ihn reporten kannst, um dem Server zu helfen.")
    })

    object WhyNoElytraInTheEnd : Faq("why-no-elytra-in-the-end", {
        text("Du fragst dich warum es im End keine Elytren gibt?")
        appendSpace()
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/faq#why-no-elytra-in-end")
        }
        text(" findest du die Antwort.")
    })

    object BenefitsAsVeteran : Faq("benefits-as-veteran", {
        text("Veteranen haben den Vorteil einer Priority Queue und kommen dadurch schneller auf den Server. Wie du den Rang bekommst, erfährst du")
        appendSpace()
        append {
            variableValue("hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/ranks-overview.html")
        }
        text(".")
    })

    object WhyNoTeleportation : Faq("why-no-teleportation", {
        text("Du fragst dich warum es auf dem Server keine Teleportation gibt?")
        appendSpace()
        append {
            variableValue("Hier")
            hoverEvent(buildText {
                spacer("Klicke, um zur Informationsseite zu gelangen.")
            })
            clickOpensUrl("https://server.castcrafter.de/faq#why-no-teleportation")
        }
        text(" findest du die Antwort.")
    })
}
