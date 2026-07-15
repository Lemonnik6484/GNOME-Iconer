### GNOME Iconer
The mod fixes the issue with minecraft's icon missing in the taskbar on GNOME

It does that by generating `.desktop` file to set the minecraft icon for `Minecraft*` and setting game's WMClass to `Minecraft*`

---
|   TT20    | Fabric/Quilt | Forge | NeoForge |
|:---------:|:------------:|:-----:|:--------:|
|   26.X    |      ✅       |   ❌   |    ✅     |
|  1.21.11  |      ✅       |   ❌   |    ✅     |
| 1.21.9/10 |      ✅       |   ❌   |    ✅     |
| 1.21.2-8  |      ✅       |   ❌   |    ⭐️    |
| 1.21(.1)  |      ✅       |   ❌   |    ⭐️    |
| 1.20.5/6  |      ✅       |   ❌   |    ⭐️    |
| 1.20.2-4  |      ✅       |   ❌   |    ⭐️    |
| 1.20(.1)  |      ✅       |  ⭐️   |    ❌     |
| 1.19.0-2  |      ✅       |   ✅   |    ❌     |
|  1.18.X   |      ❌       |   ✅   |    ❌     |

✅ - Supported
⭐️ - Workaround
❌ - Not Planned

⭐️Workaround versions work by creating a separate `.desktop` entry for each minecraft version. As I can't modify WM Class because of *stupid* early loading screen on newer forge and older neoforge versions. Alternatively you can disable early loading screen in `config/fml.toml` (`earlyWindowProvider = ""`)
