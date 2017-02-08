package luukhermans.nl.spyhunt.library;

/**
 * Created by Luuk on 7-2-2017.
 */

public class Game {

    private Player currentPlayer;
    private Region currentRegion;

    private static Game gameInstance;

    public Game(Player currentPlayer) {
        this.currentPlayer = currentPlayer;

        gameInstance = this;
    }

    public static Game getGameInstance() {
        return gameInstance;
    }

}
