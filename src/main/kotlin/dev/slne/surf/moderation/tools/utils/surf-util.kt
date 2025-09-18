package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder
import net.kyori.adventure.text.format.TextColor
import org.bukkit.event.Cancellable

fun SurfComponentBuilder.appendArtyPrefix(
    foregroundColor: TextColor = TextColor.fromHexString("#fcfcfc")!!,
    shadowColor: TextColor? = TextColor.fromHexString("#a52825")!!,
    backgroundColor: TextColor = TextColor.fromHexString("#cb312c")!!,
) {
    append(
        BitmapProvider.translateToComponent(
            "Admin",
            foregroundColor,
            shadowColor,
            backgroundColor,
        )
    )
    appendSpace()
    text("Arty", backgroundColor)
    appendSpace()
    darkSpacer(">>")
    appendSpace()
}


fun Cancellable.cancel() {
    isCancelled = true
}