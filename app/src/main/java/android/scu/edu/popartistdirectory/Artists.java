package android.scu.edu.popartistdirectory;

// Getter and Setter methods for artist name, icon and bio

public class Artists {
    private String artistName;
    private String artistIcon;
    private String artistBio;

    public Artists(String artistName, String artistIcon, String artistBio) {
        this.artistName = artistName;
        this.artistIcon = artistIcon;
        this.artistBio = artistBio;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistIcon() {
        return artistIcon;
    }

    public String getArtistBio() {
        return artistBio;
    }

}