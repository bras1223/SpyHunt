package luukhermans.nl.spyhunt.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;

import android.R.*;
import android.widget.ImageView;
import android.widget.TextView;

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
