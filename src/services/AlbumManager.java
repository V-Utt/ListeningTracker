package services;
import models.Album;
import utilities.FileManager;
import java.util.ArrayList;
import java.util.List;

public class AlbumManager {
    private List<Album> albums;

    public AlbumManager() {
        albums = FileManager.load();
    }

    public List<Album> getAlbums() {
        return new ArrayList<>(albums);
    }

}
