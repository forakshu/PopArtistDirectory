package android.scu.edu.popartistdirectory;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by akshu on 4/23/15.
 */

// Concert name, address and phone number
public class ConcertInformation extends MainActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concert_info);

        //handle to button
        button = (Button) this.findViewById(R.id.concertCall);
    }

    public void onClick(View arg0){

        // adding the call functionality
        Intent myIntent = new Intent(Intent.ACTION_CALL);
        myIntent.setData(Uri.parse("tel:8888888"));
        this.startActivity(myIntent);


    }
}