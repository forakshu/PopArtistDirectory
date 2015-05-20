package android.scu.edu.popartistdirectory;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshu on 4/23/15.
 */

//Displays the artists selected as favorite
public class FavoriteArtists extends MainActivity {

    TextView favArtistName;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_artists);

        listView = (ListView) findViewById(R.id.favListView);
        favArtistName = (TextView)findViewById(R.id.favArtistName);

        final List<Artists> artists = new ArrayList<>();

        //for all the artists marked as favorite
        if (getIntent().getExtras() != null) {
            for(String artistName : getIntent().getExtras().getStringArrayList("fav_artists")) {
                artists.add(new Artists(artistName,"",""));
            }
        }

        listView.setAdapter(new CustomAdapter(this, R.layout.custom_listview_layout, artists));
    }
}
