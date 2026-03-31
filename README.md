# Surf Moderation Tools

**Surf Moderation Tools** is a Minecraft plugin designed to help supporters and moderators work more efficiently. It
provides quick administrative and support functions directly in-game.

## ⚡ Commands

### Player Management

- **`/rotate <Player>`**   
  🔄 Rotate a player without teleporting them.
    - Permission: ``surf.moderation.tools.command.rotation``

- **`/freeze <Player> <time<s,m,h,d,w>>`**   
  ❄️ Freeze a player for a specific duration.
    - Permission: ``surf.moderation.tools.command.freeze``

- **`/unfreeze <Player>`**   
  ☀️ Unfreeze a player.
    - Permission: ``surf.moderation.tools.command.unfreeze``

- **`/pingPlayer <Player> <WithMessage>`**   
  - 📍 Ping a player with a auditory indicator and an optional message.
  - Permission: ``surf.moderation.tools.command.pingPlayer``

- **`/faq <FAQ> [Player]`**
  📄 Send pre-defined answers to frequently asked questions.
  - Permission: ``surf.moderation.tools.command.faq``
  - If no player is specified, the FAQ is sent to all relevant viewers.
  - If a player is specified, only that player receives the FAQ **and gets pinged** with a notification sound.
  - Note: The `/faq` command expects the kebab-case FAQ **id** (for example, ``veteran-benefits``, ``how-to-join``). Use in-game tab-completion to see the exact available FAQ ids. The list below shows the FAQ topics, not necessarily the literal ids you type.

  <details>
    <summary>📄 Current FAQ topics (click to expand)</summary>

    - ask
    - custom-enchantments
    - features-survival-server
    - how-to-install-voice-chat
    - how-to-join
    - how-to-open-ticket
    - information-about-the-clan-system
    - information-about-the-event-server
    - information-about-the-plots
    - information-about-the-survival-server
    - next-event
    - patience
    - read-the-docs
    - report-bug
    - report-player
    - rulebook
    - server-modpack
    - survival-events
    - take-part-in-event
    - veteran-benefits
    - when-does-the-end-open
    - when-does-the-nether-open
    - why-no-elytra-in-the-end
    - why-no-teleportation
    - why-no-villagers
  </details>
### Admin

- **`/surfmodtools setMessageCooldown <time(ms)>`**   
  ⚙️ Set the FAQ message cooldown.

- **`/surfmodtools reload`**   
  ⚙️ Reload the plugin config without restarting the server.

## 🛠 Installation

1. Download the latest release from [GitHub Releases](https://github.com/SLNE-Development/surf-moderation-tools).
2. Ensure the following dependencies are installed:
    - [Surf API](https://github.com/SLNE-Development/surf-api)
    - [Surf Bitmap Provider](https://github.com/SLNE-Development/surf-bitmap-provider)
3. Make sure **Java 25** is installed.
4. Place the plugin in your server's `plugins` folder and restart the server.

## ⚙️ Configuration

- You can configure the FAQ duration in the config file to prevent multiple supporters from sending the same FAQ at the
  same time.

## 📜 License

This project is licensed under the **GNU General Public License v3.0 (GPL-3.0)**.

---

NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.
