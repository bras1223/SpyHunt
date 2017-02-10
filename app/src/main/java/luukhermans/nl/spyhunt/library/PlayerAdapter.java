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

import com.facebook.AccessToken;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
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

            System.out.println(players.get(0).getPicture());

            String imageURL;
            Bitmap bitmap = null;

            imageURL = "http://graph.facebook.com/100003285084963/picture?type=large";
            try {
                InputStream in = (InputStream) new URL(imageURL).getContent();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Bitmap bitmap = DownloadImage("http://graph.facebook.com/" + Profile.getCurrentProfile().getId() + "/picture?type=large");
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.playerImage);
            imageView.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);
            //imageView.setImageURI(Uri.parse(players.get(pos).getPicture()));
            imageView.setImageBitmap(bitmap);

        }else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    private Bitmap DownloadImage(String picture) {
        Bitmap bitmap = null;
        InputStream in = null;
        try{
            in = OpenHttpConnection(picture);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String picture) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(picture.replace("\"", ""));
        URLConnection conn = url.openConnection();

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK){
                in = httpConn.getInputStream();
            }
        } catch (Exception ex){
            throw new IOException("Error connecting");
        }
        return in;
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
