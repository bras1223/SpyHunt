package luukhermans.nl.spyhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import luukhermans.nl.spyhunt.library.Game;
import luukhermans.nl.spyhunt.library.Player;
import luukhermans.nl.spyhunt.library.PlayerAdapter;

public class PlayerActivity extends AppCompatActivity {

    GridView gridView;

    private static List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        players = new ArrayList<>();
        players.addAll(Game.getGameInstance().getCurrentRegion().getPlayers().values());
        //players.add(new Player("gfdgfdgfgd", "Guillaime", "gfgdg"));

        gridView = (GridView) findViewById(R.id.player_grid_view);

        gridView.setAdapter(new PlayerAdapter(this, players));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.playerName)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
