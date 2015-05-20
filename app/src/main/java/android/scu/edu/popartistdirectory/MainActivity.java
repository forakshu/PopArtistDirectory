package android.scu.edu.popartistdirectory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    static List<String> fav_artists = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        final List<Artists> artists = new ArrayList<>();

        String bioRihanna = "Robyn Rihanna Fenty (born February 20, 1988), known by her stage name Rihanna is a Barbadian singer, actress, " +
                "and fashion designer. Born in Saint Michael, Barbados, her career began upon meeting record producer Evan Rogers in late 2003 " +
                "through mutual friends; she recorded demo tapes with his guidance. ";
        String bioAvril = "Avril Ramona Lavigne (born 27 September 1984) is a Canadian-French singer and songwriter. " +
                "She was born in Belleville, Ontario, and spent most of her youth in the town of Napanee. " +
                "By the age of 15, she had appeared on stage with Shania Twain; by 16, she had signed a two-album "
                + "recording contract with Arista Records worth more than $2 million. In 2002, when she was 17 years old, " +
                "Lavigne entered the music industry with her debut album Let Go, becoming one of the most popular pop punk artists";
        String bioJohn = " Roger Stephens (born December 28, 1978), better known by his stage name John Legend, is an American singer, " +
                "songwriter and actor. He has won nine Grammy Awards, one Golden Globe and one Academy Award. " +
                "In 2007, Legend received the special Starlight Award from the Songwriters Hall of Fame.";
        String bioBeyonce = "Beyoncé Giselle Knowles-Carter (born September 4, 1981) is an American singer, songwriter, and actress. " +
                "Born and raised in Houston, Texas, she performed in various singing and dancing competitions " +
                "as a child, and rose to fame in the late 1990s as lead singer of R&B girl-group Destiny's Child.";
        String bioEnrique = "Enrique Iglesias (born May 8, 1975) is a Spanish singer, songwriter, actor, and record producer."
                + "Iglesias started his career in the mid-1990s on an American Spanish Language record label Fonovisa "
                + "which helped turn him into one of the biggest stars in Latin America and the Hispanic Market in the "
                + "United States becoming the biggest seller of Spanish-language albums of that decade";
        String bioEminem = "Eminem (born October 17, 1972), is an American rapper, record producer, songwriter, and actor."
                + "In addition to his solo career, he is a member of the group D12, as well as one half of"
                + "the hip hop duo Bad Meets Evil. He is the best-selling artist of the 2000s in the United States";

        artists.add(new Artists("Rihanna", "rihanna.png", bioRihanna));
        artists.add(new Artists("Avril Lavigne", "avril.png", bioAvril));
        artists.add(new Artists("John Legend", "johnlegend.png", bioJohn));
        artists.add(new Artists("Beyonce", "beyonce.png", bioBeyonce));
        artists.add(new Artists("Enrique Iglesias", "enrique.png", bioEnrique));
        artists.add(new Artists("Eminem", "eminem.png", bioEminem));

        listView.setAdapter(new CustomAdapter(this, R.layout.custom_listview_layout, artists));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view,
                                    int position, long id) {

                Artists artist;
                artist = artists.get(position);

                //Passing the details of the artist clicked

                Intent intent = new Intent(MainActivity.this, GetArtistDetails.class);
                Bundle bundle = new Bundle();

                bundle.putString("name", artist.getArtistName());
                bundle.putString("image", artist.getArtistIcon());
                bundle.putString("bio", artist.getArtistBio());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        //Implementing the 'favorite' functionality on long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Artists artist = artists.get(position); //PD
                TextView clickedView = (TextView) view.findViewById(R.id.artistName);
                Dialog(artist);
                return true;
            }
        });
    }

    public void Dialog(Artists artist) {
        final Artists selectedArtist = artist;
        AlertDialog.Builder dialogAlert = new AlertDialog.Builder(MainActivity.this);

        //checking whether the artist has already been added to favorites list
        //If not added to favorite list, prompts whether to add or not

        if (!fav_artists.contains(selectedArtist.getArtistName())) {
            dialogAlert.setMessage("Do you want to add this to favorites? ");
            //Yes;  added to the list
            dialogAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (!fav_artists.contains(selectedArtist.getArtistName())) {
                        fav_artists.add(selectedArtist.getArtistName());
                    }
                    Toast.makeText(getApplicationContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                    dialog.cancel();

                }
            });
            //No; no action
            dialogAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog

                    dialog.cancel();

                }
            });
        }
        //Artist already exists in the favorite list
        else {
            dialogAlert.setMessage("This artist has already been added to Favorite list");
        }

        dialogAlert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {

            case R.id.action_info:
                Intent intentInfo = new Intent(this, ConcertInformation.class);
                this.startActivity(intentInfo);
                break;

            case R.id.action_favorite:
                Intent intentFav = new Intent(this, FavoriteArtists.class);
                intentFav.putStringArrayListExtra("fav_artists", (ArrayList<String>) fav_artists);
                this.startActivity(intentFav);
                break;

            case R.id.action_uninstall:
                Uri packageURI = Uri.parse("package:android.scu.edu.popartistdirectory");
                Intent intentUninstall = new Intent(Intent.ACTION_DELETE, packageURI);
                this.startActivity(intentUninstall);
                break;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
