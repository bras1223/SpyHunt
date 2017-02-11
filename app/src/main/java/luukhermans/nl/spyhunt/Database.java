package luukhermans.nl.spyhunt;

import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import luukhermans.nl.spyhunt.library.Game;
import luukhermans.nl.spyhunt.library.Player;
import luukhermans.nl.spyhunt.library.Region;

/**
 * Created by Luuk on 8-2-2017.
 */

public class Database {

    private static Database databaseInstance = null;
    private static FirebaseDatabase database;

    private static DatabaseReference ref;
    private static Context context;

    protected Database() {
    }

    public static Database getDatabaseInstance(Context context1) {
        if (databaseInstance == null) {
            context = context1;
            databaseInstance = new Database();
            database = FirebaseDatabase.getInstance();

            final Region region = Game.getGameInstance().getCurrentRegion();
            ref = database.getReference("region").child(Game.getGameInstance().getCurrentRegion().getName());

            ref.child("players").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Player player = dataSnapshot.getValue(Player.class);
                    region.addPlayer(player);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Player player = dataSnapshot.getValue(Player.class);
                    Player currentPlayer = Game.getGameInstance().getCurrentPlayer();

                    if (player.getUid().equals(currentPlayer.getUid())) {
                        if (player.getScore() != currentPlayer.getScore()) {
                            region.editPlayer(player);
                            Game game = Game.getGameInstance();
                            game.setCurrentPlayer(player);


                        }
                    }
                    region.editPlayer(player);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


        return databaseInstance;
    }

    public void signinPlayer(Player player) {
        Game game = Game.getGameInstance();
        if(game.getCurrentRegion().getPlayers().containsKey(player.getUid())) {
            game.setCurrentPlayer(game.getCurrentRegion().getPlayers().get(player.getUid()));
        }
        else {
            ref.child("players").child(player.getUid()).setValue(player);
            Game.getGameInstance().setCurrentPlayer(player);
        }
    }

    public void update(Player player) {
        ref.child("players").child(player.getUid()).setValue(player);
    }

}
