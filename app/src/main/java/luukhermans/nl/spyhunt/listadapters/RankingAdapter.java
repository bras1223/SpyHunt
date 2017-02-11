package luukhermans.nl.spyhunt.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import luukhermans.nl.spyhunt.R;
import luukhermans.nl.spyhunt.library.Player;

/**
 * Created by guill on 10-2-2017.
 */

public class RankingAdapter extends BaseAdapter {

    private Context context;
    private final List<Player> players;

    public RankingAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView;

        if(convertView == null){

            listView = new View(context);
            listView = inflater.inflate(R.layout.ranking_list_item, null);

            TextView rankingNumber = (TextView) listView
                    .findViewById(R.id.rankingNumber);
            rankingNumber.setText(position + 1 + ".");

            TextView rankingName = (TextView) listView
                    .findViewById(R.id.rankingName);
            rankingName.setText(players.get(position).getName());

            TextView rankingScore = (TextView) listView
                    .findViewById(R.id.rankingScore);
            rankingScore.setText("(" + players.get(position).getScore() + ")");

        }else{
            listView = (View) convertView;
        }

        return listView;
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
