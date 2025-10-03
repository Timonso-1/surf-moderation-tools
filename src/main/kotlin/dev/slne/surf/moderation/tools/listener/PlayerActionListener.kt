package dev.slne.surf.moderation.tools.listener

import com.github.benmanes.caffeine.cache.Caffeine
import dev.slne.surf.moderation.tools.service.freezeService
import dev.slne.surf.moderation.tools.utils.cancel
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import java.util.*
import java.util.concurrent.TimeUnit

class PlayerActionListener : Listener {
    private val messageCooldown = Caffeine.newBuilder()
        .expireAfterWrite(5, TimeUnit.SECONDS)
        .build<UUID, Long>()

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        val player = event.player

        if (freezeService.isFrozen(player.uniqueId)) {
            sendResultMessage(player, "Du bist eingefroren und kannst dich nicht bewegen.")

            if (!event.hasChangedBlock()) {
                return
            }

            event.cancel()
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player

        if (freezeService.isFrozen(player.uniqueId)) {
            sendResultMessage(player, "Du bist eingefroren und kannst nichts abbauen.")
            event.cancel()
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player

        if (freezeService.isFrozen(player.uniqueId)) {
            sendResultMessage(player, "Du bist eingefroren und kannst nichts platzieren.")
            event.cancel()
        }
    }

    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        val damager = event.damager

        if (damager !is Player) {
            return
        }

        if (freezeService.isFrozen(damager.uniqueId)) {
            sendResultMessage(damager, "Du bist eingefroren und kannst niemanden angreifen.")
            event.cancel()
        }
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player = event.player

        if (freezeService.isFrozen(player.uniqueId)) {
            sendResultMessage(player, "Du bist eingefroren und kannst nichts benutzen.")
            event.cancel()
        }
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEntityEvent) {
        val player = event.player

        if (freezeService.isFrozen(player.uniqueId)) {
            sendResultMessage(player, "Du bist eingefroren und kannst nichts benutzen.")
            event.cancel()
        }
    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        val entity = event.entity
        if (entity !is Player) {
            return
        }

        if (freezeService.isFrozen(entity.uniqueId)) {
            event.cancel()
        }
    }

    private fun sendResultMessage(player: Player, message: String) {
        if ((messageCooldown.getIfPresent(player.uniqueId) ?: 0) < System.currentTimeMillis()) {
            player.sendText {
                appendPrefix()
                error(message)
            }
            messageCooldown.put(player.uniqueId, System.currentTimeMillis() + 5_000)
        }
    }
}