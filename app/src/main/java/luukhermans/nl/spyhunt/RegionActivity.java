package luukhermans.nl.spyhunt;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import luukhermans.nl.spyhunt.library.Game;
import luukhermans.nl.spyhunt.library.Region;

public class RegionActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        Region region = Game.getGameInstance().getCurrentRegion();

        TextView lblRegion = (TextView) findViewById(R.id.lblRegion);
        lblRegion.setText("Region: " + region.getName());

        ImageView imgProfile = (ImageView) findViewById(R.id.imgProfile);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegionActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        TextView lblRegionPlayers = (TextView) findViewById(R.id.lblCount);
        lblRegionPlayers.setText(String.valueOf(region.getPlayers().size()));

        Button btnSpot = (Button) findViewById(R.id.btnSpot);
        btnSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegionActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });

    }
}
