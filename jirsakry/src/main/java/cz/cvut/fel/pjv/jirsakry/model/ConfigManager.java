package cz.cvut.fel.pjv.jirsakry.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Utility class for saving and loading game configuration to and from a JSON file.
 */
public class ConfigManager {
    private static final String CONFIG_FILE = "config.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    private final static Logger LOGGER = Logger.getLogger(ConfigManager.class.getName());

    /**
     * Saves the given game configuration to a JSON file.
     *
     * @param config the game configuration to save
     */
    public static void saveConfig(GameConfig config) {
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(CONFIG_FILE), config);
        } catch (IOException e) {
            LOGGER.warning("Error saving config: " + e.getMessage());
        }
    }

    /**
     * Loads the game configuration from a JSON file.
     * If the file does not exist or loading fails, returns the default configuration.
     *
     * @return loaded or default game configuration
     */
    public static GameConfig loadConfig() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            GameConfig defaultConfig = GameConfig.getDefaultConfig();
            saveConfig(defaultConfig);
            return defaultConfig;
        }
        try {
            return mapper.readValue(file, GameConfig.class);
        } catch (IOException e) {
            LOGGER.warning("Error loading config: " + e.getMessage());
            return GameConfig.getDefaultConfig();
        }
    }
}
