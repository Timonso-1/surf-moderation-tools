package dev.slne.surf.moderation.tools.service

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.shynixn.mccoroutine.folia.launch
import dev.slne.surf.moderation.tools.faq.Faq
import dev.slne.surf.moderation.tools.plugin
import dev.slne.surf.moderation.tools.utils.appendArtyPrefix
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surf.surfapi.core.api.messages.adventure.sound
import kotlinx.coroutines.Dispatchers
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

class FaqService {
    private val cooldown get() = plugin.moderationToolConfig.config.faqCooldown
    private val messageCooldownCache = Caffeine.newBuilder()
        .expireAfterWrite(cooldown, TimeUnit.MILLISECONDS)
        .build<Faq, Long>()

    fun sendFaq(
        executor: Player,
        faq: Faq,
        targets: Collection<Player> = emptyList()
    ) = plugin.launch(Dispatchers.IO) {
        if ((messageCooldownCache.getIfPresent(faq) ?: 0) < System.currentTimeMillis()) {
            messageCooldownCache.put(faq, System.currentTimeMillis() + cooldown)

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

        executor.sendText {
            appendPrefix()
            error("Du musst noch warten, bevor du den Faq-Eintrag verwendest.")
        }
    }

    companion object {
        val INSTANCE = FaqService()
    }
}


val faqService get() = FaqService.INSTANCE