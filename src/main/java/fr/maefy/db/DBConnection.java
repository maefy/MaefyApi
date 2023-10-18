package fr.maefy.db;

import fr.maefy.api.API;
import redis.clients.jedis.Jedis;

import java.util.logging.Logger;

public class DBConnection {
    String address;
    int port;
    Jedis jedis;

    public DBConnection(String address, int port) {
        this.address = address;
        this.port = port;
        // Établir une connexion à Redis
        jedis = new Jedis(address, port);

        // Tester la connexion en vérifiant la disponibilité du serveur Redis
        String response = jedis.ping();

        API.logger.info("Réponse du serveur Redis : " + response);
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public Jedis getJedis() {
        return jedis;
    }
}
