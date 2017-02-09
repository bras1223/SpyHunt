package luukhermans.nl.spyhunt.library;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luuk on 7-2-2017.
 */

public class Region {

    private String name;
    private String location;
    private Map<String, Player> playersList;

    public Region (String name) {
        this.name = name;
        this.playersList = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        this.playersList.put(player.getUid(), player);
    }

    public Map<String, Player> getPlayers() {
        return playersList;
    }

    public void editPlayer(Player player) {
        playersList.remove(player.getUid());
        playersList.put(player.getUid(), player);
    }
}
