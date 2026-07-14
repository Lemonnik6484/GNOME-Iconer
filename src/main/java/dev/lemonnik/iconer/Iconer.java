package dev.lemonnik.iconer;

//? if fabric {
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
//?}

//? if forge {
/*import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
*///?}

//? if neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
*///?}

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import net.minecraft.SharedConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.lemonnik.iconer.Iconer.MOD_ID;

//? if forge || neoforge
//@Mod(MOD_ID)

public class Iconer
		//? if fabric
		implements ModInitializer

		{
	public static final String MOD_ID = /*$ mod_id */"iconer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Path TARGET_ICON_PATH = Path.of(getGameDir() + "/minecraft.png");
	private static final Path DESKTOP_ENTRY_PATH = Path.of(System.getProperty("user.home") + "/.local/share/applications/minecraft-iconer.desktop");

	private static boolean allGood = false;

	//? if fabric
	@Override public void onInitialize()

	//? if neoforge
	 //public Iconer(IEventBus modBus)

	//? if forge
	//public Iconer()

	{
		if (allGood) {
			LOGGER.info("Iconer started, but all work has been done earlier! (̿▀̿‿ ̿▀̿ ̿)");
		} else {
			LOGGER.warn("Iconer started! Seems like early code failed, but we can try in later! (now) (︶︹︶)");
			if (genFiles(false)) {
				LOGGER.info("It's all good now! Have fun! <(^_^)>");
			} else {
				LOGGER.warn("It's still not working, last try... (╥﹏╥)");
				if (genFiles(true)) {
					LOGGER.info("Fallback complete! Should be good now! (✿◠‿◠)");
				} else {
					LOGGER.warn("Fallback failed! You're on your own now. (⊙＿⊙')");
				}
			}
		}
	}

	public static boolean genFiles(boolean fallback) {
		if (!isCompatible() && !fallback) {
			return false;
		}
		try {
			Process process = new ProcessBuilder("gnome-shell", "--version").start();
			String output = new String(process.getInputStream().readAllBytes());
			process.waitFor();

			if (output.toUpperCase().contains("GNOME")) {
                LOGGER.info("Detected {}", output);

				Files.write(TARGET_ICON_PATH, loadIconBytes());
				LOGGER.info("Wrote minecraft.png to {}", TARGET_ICON_PATH);

				if (!fallback) {
					if (Files.exists(DESKTOP_ENTRY_PATH)) {
						Files.delete(DESKTOP_ENTRY_PATH);
					}

					Files.createFile(DESKTOP_ENTRY_PATH);
					Files.writeString(DESKTOP_ENTRY_PATH,
							"[Desktop Entry]\n" +
									"Type=Application\n" +
									"Icon=" + TARGET_ICON_PATH + "\n" +
									"StartupWMClass=Minecraft*"
					);
					LOGGER.info("Created minecraft-iconer.desktop in {}", DESKTOP_ENTRY_PATH);
				} else {
					//? if >=1.21.9 {
					String version = SharedConstants.getCurrentVersion().name();
					//?} else {
					/*String version = SharedConstants.getCurrentVersion().getName();
					*///?}
					Path desktop = Path.of(System.getProperty("user.home") + "/.local/share/applications/minecraft-iconer-" + version + ".desktop");

					if (Files.exists(desktop)) {
						Files.delete(desktop);
					}

					Files.createFile(desktop);
					Files.writeString(desktop,
							"[Desktop Entry]\n" +
									"Type=Application\n" +
									"Icon=" + TARGET_ICON_PATH + "\n" +
									"StartupWMClass=Minecraft* " + version
					);
                    LOGGER.info("Created minecraft-iconer-{}.desktop in {}", version, desktop);
				}

				allGood = true;
				return true;
			} else {
				LOGGER.warn("GNOME not detected");
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Failed to complete tasks");
			e.printStackTrace();
			return false;
		}
	}

	private static Path getGameDir() {
		//? if fabric {
		return FabricLoader.getInstance().getGameDir();
		//?} else if forge || neoforge {
		/*return FMLPaths.GAMEDIR.get();
		 *///?}
	}

	private static byte[] loadIconBytes() {
		try (InputStream is = Iconer.class.getResourceAsStream("/minecraft.png")) {
			if (is == null) {
				throw new IllegalStateException("minecraft.png not found on classpath");
			}
			return is.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException("Failed to load icon resource", e);
		}
	}

	private static boolean isCompatible() {
		//? if fabric {
		return true;
		//?} else if forge {
			/*//? if >1.19.2 {
				return false;
			//?} else {
				/^return true;
			^///?}
		 *///?} else if neoforge {
			/*//? if >1.21.9 {
				/^return true;
			^///?} else {
				return false;
			//?}
		 *///?}
	}
}