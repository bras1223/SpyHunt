package luukhermans.nl.spyhunt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import luukhermans.nl.spyhunt.library.Game;
import luukhermans.nl.spyhunt.library.Player;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Game game = Game.getGameInstance();

        TextView lblPoints = (TextView) findViewById(R.id.lblPoints);
        lblPoints.setText(String.valueOf(game.getCurrentPlayer().getScore()));

        Player player1 = new Player("", "", "");
        Player player2 = new Player("", "", "");
        Player player3 = new Player("", "", "");

        for (Player p : Game.getGameInstance().getCurrentRegion().getPlayers().values()) {
            if (p.getScore() > player3.getScore()) {
                player3 = p;
            } if (p.getScore() > player2.getScore()) {
                player2 = p;
            } if (p.getScore() > player1.getScore()) {
                player1 = p;
            }
        }

        TextView lblPlayer1 = (TextView) findViewById(R.id.lblPlayer1);
        TextView lblPlayer2 = (TextView) findViewById(R.id.lblPlayer2);
        TextView lblPlayer3 = (TextView) findViewById(R.id.lblPlayer3);

        lblPlayer1.setText(player1.getName());
        lblPlayer2.setText(player2.getName());
        lblPlayer3.setText(player3.getName());

    }
}
