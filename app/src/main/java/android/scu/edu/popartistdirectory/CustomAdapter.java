package android.scu.edu.popartistdirectory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//to populate the listview with image and text
public class CustomAdapter extends ArrayAdapter<Artists> {
    private final List<Artists> artists;
    public CustomAdapter(Context context, int resource, List<Artists> artists) {
        super(context, resource, artists);
        this.artists = artists;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_listview_layout, null);
        TextView textView = (TextView) row.findViewById(R.id.artistName);

        //Sets the name of the artist
        textView.setText(artists.get(position).getArtistName());
        try {

            //handle for image
            ImageView imageView = (ImageView) row.findViewById(R.id.artistImage);
            //fetches the image from the assets folder
            InputStream inputStream = getContext().getAssets().open(artists.get(position).getArtistIcon());
            //draws the image
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}