package luukhermans.nl.spyhunt.library;

import android.location.Location;

import luukhermans.nl.spyhunt.Database;

/**
 * Created by Luuk on 7-2-2017.
 */

public class Game {

    private Player currentPlayer;
    private static Region currentRegion;

    private static Game gameInstance = null;

    protected Game() {
    }

    public void setCurrentPlayer (Player currentPlayer) {

        this.currentPlayer = currentPlayer;
    }

    public void setCurrentRegion (Region region) {
        this.currentRegion = region;
    }
    public static Game getGameInstance() {
        if (gameInstance == null) {
            gameInstance = new Game();
            currentRegion = new Region("Strijp-S");
        }

        return gameInstance;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setLocation(Location location) {
        currentPlayer.setLocation(location);
        Database.getDatabaseInstance().update(currentPlayer);
    }

    public Region getCurrentRegion() {
        return currentRegion;
    }
}
