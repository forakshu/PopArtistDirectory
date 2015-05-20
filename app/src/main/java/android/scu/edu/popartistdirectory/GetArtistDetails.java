package android.scu.edu.popartistdirectory;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by akshu on 4/23/15.
 */

//Gets the details for each artist and displays on screen
public class GetArtistDetails extends MainActivity {

    TextView artistName;
    ImageView artistImg;
    TextView artistBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_details);


        //getting handles to the GUI elements
        artistName = (TextView) findViewById(R.id.artistName);
        artistImg = (ImageView) findViewById(R.id.artistImage);
        artistBio = (TextView) findViewById(R.id.artistBio);

        //Fetching the data from the intent
        Intent myCallerIntent = getIntent();
        Bundle myBundle = myCallerIntent.getExtras();
        String artName = myBundle.getString("name");
        Drawable artImg = Drawable.createFromPath(myBundle.getString("image"));
        String artBio = myBundle.getString("bio");

        //Setting the fetched data to display on screen
        String uri = null;
        try {
            InputStream inputStream   = getAssets().open(myBundle.getString("image"));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            artistImg.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        artistName.setText(artName);
        artistBio.setText(artBio);


    }
}