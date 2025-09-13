package dev.slne.surfModerationTools.listener

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import dev.slne.surfModerationTools.plugin
import dev.slne.surfModerationTools.utils.FreezeManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.concurrent.TimeUnit

class PlayerActionListener : Listener {

    private val messageCooldown = Caffeine.newBuilder()
        .expireAfterWrite(2, TimeUnit.SECONDS)
        .build<UUID, Long>()

    private fun sendFrozenMessage(player: Player, message: String) {
        if (messageCooldown.getOrDefault(player.uniqueId, 0L) < System.currentTimeMillis) {
            player.sendText {
                appendPrefix()
                error(message)
            }
            messageCooldown.put(player.uniqueId, System.currentTimeMillis() + 3_000)
        }
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        plugin.launch(plugin.entityDispatcher(event.player)) {
            if (FreezeManager.isPlayerFrozen(player)) {
                sendFrozenMessage(player, "Du bist eingefroren und kannst dich nicht bewegen.")
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onPlayerBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        plugin.launch(plugin.entityDispatcher(event.player)) {
            if (FreezeManager.isPlayerFrozen(player)) {
                sendFrozenMessage(player, "Du bist eingefroren und kannst nichts abbauen.")
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onPlayerBlockPlace(event: BlockPlaceEvent) {
        val player = event.player
        plugin.launch(plugin.entityDispatcher(player)) {
            if (FreezeManager.isPlayerFrozen(player)) {
                sendFrozenMessage(player, "Du bist eingefroren und kannst nichts platzieren.")
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onPlayerAttack(event: EntityDamageByEntityEvent) {
        val damager = event.damager
        plugin.launch(plugin.entityDispatcher(damager)) {
            if (damager is Player) {
                sendFrozenMessage(damager, "Du bist eingefroren und kannst niemanden angreifen.")
                if (FreezeManager.isPlayerFrozen(damager)) {
                    event.isCancelled = true
                }
            }
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        plugin.launch(plugin.entityDispatcher(player)) {
            if (FreezeManager.isPlayerFrozen(player)) {
                sendFrozenMessage(player, "Du bist eingefroren und kannst nichts benutzen.")
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEntityEvent) {
        val player = event.player
        plugin.launch(plugin.entityDispatcher(player)) {
            if (FreezeManager.isPlayerFrozen(player)) {
                sendFrozenMessage(player, "Du bist eingefroren und kannst nichts benutzen.")
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val player = event.player
        plugin.launch(plugin.entityDispatcher(player)) {
            FreezeManager.unfreezePlayer(player)
        }
    }
}