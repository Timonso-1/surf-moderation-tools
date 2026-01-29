package dev.slne.surf.moderation.tools.utils

import dev.slne.surf.bitmap.common.provider.BitmapProvider
import dev.slne.surf.surfapi.core.api.messages.builder.SurfComponentBuilder
import net.kyori.adventure.text.format.ShadowColor
import net.kyori.adventure.text.format.TextColor

fun SurfComponentBuilder.appendArtyPrefix(
    foregroundColor: TextColor = TextColor.color(0xfcfcfc),
    shadowColor: ShadowColor = ShadowColor.shadowColor(0xa52825),
    backgroundColor: TextColor = TextColor.color(0xcb312c),
) {
    append(
        BitmapProvider.translateToComponent(
            "ARTY",
            foregroundColor,
            backgroundColor
        )
    )
    appendSpace()
    text("Arty", backgroundColor)
    appendSpace()
    darkSpacer(">>")
    appendSpace()
}