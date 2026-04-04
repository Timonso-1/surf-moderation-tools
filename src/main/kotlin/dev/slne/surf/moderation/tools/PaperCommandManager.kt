package dev.slne.surf.moderation.tools

import dev.slne.surf.moderation.tools.commands.faqCommand
import dev.slne.surf.moderation.tools.commands.freezeCommand
import dev.slne.surf.moderation.tools.commands.pingPlayerCommand
import dev.slne.surf.moderation.tools.commands.rotateCommand
import dev.slne.surf.moderation.tools.commands.stopInteraction
import dev.slne.surf.moderation.tools.commands.surfModerationToolsCommand
import dev.slne.surf.moderation.tools.commands.unfreezeCommand

class PaperCommandManager {
    fun registerCommands() {
        surfModerationToolsCommand()
        faqCommand()
        rotateCommand()
        freezeCommand()
        unfreezeCommand()
        stopInteraction()
        pingPlayerCommand()
    }
}