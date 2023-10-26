package fr.maefy.player;

import java.util.UUID;

public class Player {
    String playerName;
    UUID playerUUID;

    public Player(String playerName, UUID playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    public String getName() {
        return playerName;
    }

    public UUID getUniqueId() {
        return playerUUID;
    }
}
