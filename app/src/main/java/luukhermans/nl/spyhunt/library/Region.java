package luukhermans.nl.spyhunt.library;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import luukhermans.nl.spyhunt.Database;

/**
 * Created by Luuk on 7-2-2017.
 */

public class Region {

    private String name;
    private String location;
    private Map<String, Player> playersList;

    public Region(String name) {
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
        player.setDistance(distance(player));
        playersList.put(player.getUid(), player);
    }

    public boolean exposePlayer(Player player) {
        Player currentPlayer = Game.getGameInstance().getCurrentPlayer();
        if (player.getDistance() < 20 && player.getLastExposedUidBy() != currentPlayer.getUid() && currentPlayer.getLastExposedUidBy() != player.getUid()) {
            currentPlayer.updateScore(10);
            player.updateScore(-5);
            player.changeLevel(1);
            currentPlayer.changeLevel(-1);
            currentPlayer.setLastExposed(player.getUid());
            player.setLastExposedUidBy(currentPlayer.getUid());

            Context context = null;
            Database data = Database.getDatabaseInstance(context);
            data.update(player);
            data.update(currentPlayer);
            return true;
        }
        return false;
    }


    private double distance(Player player) {
        Player user = Game.getGameInstance().getCurrentPlayer();

        double lon1 = user.getLongtitude();
        double lat1 = user.getLatitude();
        double el1 = user.getAltitude();
        double lon2 = player.getLongtitude();
        double lat2 = player.getLatitude();
        double el2 = player.getAltitude();

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
