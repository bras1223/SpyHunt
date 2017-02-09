package luukhermans.nl.spyhunt;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import luukhermans.nl.spyhunt.library.Game;
import luukhermans.nl.spyhunt.library.Region;

public class NearbyActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        Region region = Game.getGameInstance().getCurrentRegion();

        ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);
        ImageView imgUsers = (ImageView) findViewById(R.id.imgUsers);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        imgUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                startActivity(intent);
            }
        });

        TextView lblRegion = (TextView) findViewById(R.id.lblRegion);
        lblRegion.setText("Region: " + region.getName());

        //TODO OPEN CAMERA APP
    }
}
