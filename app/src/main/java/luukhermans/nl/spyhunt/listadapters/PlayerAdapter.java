package luukhermans.nl.spyhunt.listadapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import luukhermans.nl.spyhunt.R;
import luukhermans.nl.spyhunt.library.Player;

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
    public View getView(final int pos, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if(convertView == null){

            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_view_list_item, null);

            TextView textView = (TextView) gridView
                    .findViewById(R.id.playerName);
            textView.setText(players.get(pos).getName());

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<Bitmap> callable = new Callable<Bitmap>() {
                @Override
                public Bitmap call() throws Exception {

                    Bitmap bitmap = null;
                    try {
                        URL imageURL = new URL(players.get(pos).getPicture());
                        bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return bitmap;
                }
            };
            Future<Bitmap> future = executor.submit(callable);

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.playerImage);

            try {
                imageView.setImageBitmap(future.get());
                imageView.setMinimumWidth(500);
                imageView.setMinimumHeight(470);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            executor.shutdown();

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
