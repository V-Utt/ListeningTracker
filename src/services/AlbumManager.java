package services;
import models.Album;
import utilities.FileManager;
import java.util.ArrayList;

public class AlbumManager {
    private ArrayList<Album> albums;

    public AlbumManager() {
        albums = FileManager.load();
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public boolean addAlbum(Album album) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(album.getId())) {
                return false;
            }
        }

        albums.add(album);
        FileManager.save(albums);
        return true;
    }

    public boolean removeAlbum(String id) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(id)) {
                albums.remove(i);
                FileManager.save(albums);
                return true;
            }
        }
        return false;
    }
}
