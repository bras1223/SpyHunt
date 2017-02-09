package luukhermans.nl.spyhunt.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;

import android.R.*;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.URL;
import java.util.List;

import luukhermans.nl.spyhunt.R;

/**
 * Created by guill on 9-2-2017.
 */

public class PlayerAdapter extends BaseAdapter {

    private Context context;
    private final List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if(convertView == null){

            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_view_list_item, null);

            TextView textView = (TextView) gridView
                    .findViewById(R.id.playerName);
            textView.setText(players.get(pos).getName());

            ImageView imageView = (ImageView) gridView.findViewById(R.id.playerIcon);
            imageView.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);


        }else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
