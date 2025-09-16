package dev.slne.surf.moderation.tools.commands

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.shynixn.mccoroutine.folia.launch
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.Faq
import dev.slne.surf.moderation.tools.utils.ModPermissionRegistry
import dev.slne.surf.moderation.tools.utils.appendArtyPrefix
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.messages.adventure.sound
import kotlinx.coroutines.Dispatchers
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

private val messageCooldown = Caffeine.newBuilder()
    .expireAfterWrite(5, TimeUnit.SECONDS)
    .build<Faq, Long>()

fun faqCommand() = commandAPICommand("faq") {
    withPermission(ModPermissionRegistry.COMMAND_FAQ)
    multiLiteralArgument("faq", *Faq.ALL_FAQS.map { it.name }.toTypedArray())
    entitySelectorArgumentManyPlayers("targets", allowEmpty = true, optional = true)

    playerExecutor { player, args ->
        val faq: String by args
        val targets: Collection<Player>? by args
        val usableTargets = targets ?: emptyList()

        val faqEntry = Faq.getFaqByName(faq) ?: run {
            player.sendText {
                appendPrefix()
                error("Es wurde kein FAQ-Eintrag mit dem Namen ")
                variableValue(faq)
                error(" gefunden.")
            }

            return@playerExecutor
        }

        sendFaqMessage(player, faqEntry, usableTargets)
    }
}

private fun sendFaqMessage(player: Player, faq: Faq, targets: Collection<Player> = emptyList()) = plugin.launch(Dispatchers.IO) {
        if ((messageCooldown.getIfPresent(faq) ?: 0) < System.currentTimeMillis()) {
            messageCooldown.put(faq, System.currentTimeMillis() + 5_000)

            if (targets.isNotEmpty()) {
                targets.forEach {
                    it.sendText {
                        appendArtyPrefix()
                        append {
                            text(it.name)
                            decorate(TextDecoration.BOLD)
                        }
                        appendSpace()
                        append(faq.message)
                    }

                    it.playSound(sound {
                        type(Sound.BLOCK_NOTE_BLOCK_BELL)
                        source(net.kyori.adventure.sound.Sound.Source.PLAYER)
                        volume(1f)
                        pitch(1f)
                    }, net.kyori.adventure.sound.Sound.Emitter.self())
                }
                return@launch
            }

            server.sendText {
                appendArtyPrefix()
                append(faq.message)
            }
            return@launch
        }

        player.sendText {
            appendPrefix()
            error("Du musst noch warten, bevor du den Faq-Eintrag verwendest.")
        }
    }
}