package dev.slne.surf.moderation.tools.listener

import com.github.benmanes.caffeine.cache.Caffeine
import com.sksamuel.aedile.core.expireAfterWrite
import dev.slne.surf.moderation.tools.service.FreezeService
import dev.slne.surf.surfapi.bukkit.api.event.cancel
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import java.util.*
import kotlin.time.Duration.Companion.seconds

object PlayerActionListener : Listener {
    private val messageCooldown = Caffeine.newBuilder()
        .expireAfterWrite(5.seconds)
        .build<UUID, Unit>()

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        if (!event.hasChangedBlock()) return

        val player = event.player
        if (FreezeService.isFrozen(player.uniqueId)) {
            event.cancel()
            sendResultMessage(player, "Du bist eingefroren und kannst dich nicht bewegen.")
        }
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player

        if (FreezeService.isFrozen(player.uniqueId)) {
            event.cancel()
            sendResultMessage(player, "Du bist eingefroren und kannst nichts abbauen.")
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player

        if (FreezeService.isFrozen(player.uniqueId)) {
            event.cancel()
            sendResultMessage(player, "Du bist eingefroren und kannst nichts platzieren.")
        }
    }

    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        val damager = event.damager

        if (damager !is Player) {
            return
        }

        if (FreezeService.isFrozen(damager.uniqueId)) {
            event.cancel()
            sendResultMessage(damager, "Du bist eingefroren und kannst niemanden angreifen.")
        }
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player = event.player

        if (FreezeService.isFrozen(player.uniqueId)) {
            event.cancel()
            sendResultMessage(player, "Du bist eingefroren und kannst nichts benutzen.")
        }
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEntityEvent) {
        val player = event.player

        if (FreezeService.isFrozen(player.uniqueId)) {
            event.cancel()
            sendResultMessage(player, "Du bist eingefroren und kannst nichts benutzen.")
        }
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        val entity = event.entity
        if (entity !is Player) {
            return
        }
        if (FreezeService.isFrozen(entity.uniqueId)) {
            event.cancel()
        }
    }

    private fun sendResultMessage(player: Player, message: String) {
        if (messageCooldown.getIfPresent(player.uniqueId) != null) return
        messageCooldown.put(player.uniqueId, Unit)

        player.sendText {
            appendErrorPrefix()
            error(message)
        }
    }
}