package luukhermans.nl.spyhunt;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

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

    protected Database() {
    }

    public static Database getDatabaseInstance() {
        if (databaseInstance == null) {
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

                    if(player.getUid().equals(currentPlayer.getUid())) {
                        if(player.getLastExposedUidBy() != currentPlayer.getLastExposedUidBy()) {

                            region.editPlayer(player);
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
        final Player newPlayer = player;
        ref.child("players").child(player.getUid()).runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Player oldPlayer = (Player) mutableData.getValue();

                if(oldPlayer == null) {
                    mutableData.setValue(newPlayer);
                    Game.getGameInstance().setCurrentPlayer(newPlayer);
                    return Transaction.success(mutableData);
                } else {
                    Game.getGameInstance().setCurrentPlayer(oldPlayer);
                }

                return Transaction.abort();
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }

    public void update(Player player) {
        ref.child("players").setValue(player);
    }


/*    public void postOnWall(String msg) {
        Log.d("Tests", "Testing graph API wall post");
        try {
            String response = mFacebook.request("me");
            Bundle parameters = new Bundle();
            parameters.putString("message", msg);
            parameters.putString("description", "test test test");
            response = mFacebook.request("me/feed", parameters,
                    "POST");
            Log.d("Tests", "got response: " + response);
            if (response == null || response.equals("") ||
                    response.equals("false")) {
                Log.v("Error", "Blank response");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/
}
