package de.pilz.anotheroregenerator.configuration;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;

import de.pilz.anotheroregenerator.AnotherOreGenerator;

public class ConfigManager {

    public static void initConfig() {
        try {
            ConfigurationManager.registerConfig(GeneralConfig.class);
            ConfigurationManager.registerConfig(DefaultOreGenConfig.class);
        } catch (ConfigException ex) {
            AnotherOreGenerator.LOG.error("Error registering config classes to GTNH' ConfigurationManager.", ex);
        }
    }
}
