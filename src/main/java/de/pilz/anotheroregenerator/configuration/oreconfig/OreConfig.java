package de.pilz.anotheroregenerator.configuration.oreconfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import com.google.gson.Gson;

import de.pilz.anotheroregenerator.AnotherOreGenerator;

public class OreConfig {

    private final HashSet<OreConfigEntry> entries = new HashSet<OreConfigEntry>();

    public void registerOre(OreConfigEntry config) {
        entries.add(config);
    }

    public OreConfigEntry[] getOres() {
        return entries.toArray(new OreConfigEntry[entries.size()]);
    }

    public static OreConfig loadConfig(String folderPath) {
        Gson gson = new Gson();
        OreConfig config = new OreConfig();

        File[] files = new File(folderPath).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName()
                    .toLowerCase()
                    .endsWith(".json")) {
                    try {
                        String raw = new String(Files.readAllBytes(Paths.get(file.getPath())));
                        OreConfigEntry[] entries = gson.fromJson(raw, OreConfigEntry[].class);

                        if (entries != null) {
                            for (OreConfigEntry entry : entries) {
                                config.registerOre(entry);
                            }
                        } else {
                            AnotherOreGenerator.LOG
                                .error("Ore config file can not be read! It probably has an invalid format.");
                        }
                    } catch (IOException ex) {
                        AnotherOreGenerator.LOG.error("Ore config file can not be read!", ex);
                    }
                }
            }
        } else {
            AnotherOreGenerator.LOG.error("Ore config directory does not exists: " + folderPath);
        }

        return config;
    }
}
