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

    public boolean addAlbum(Album album) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(album.getId())) {
                return false;
            }
        }

        albums.add(album);
        return true;
    }

    public boolean removeAlbum(String id) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(id)) {
                albums.remove(i);
                return true;
            }
        }
        return false;
    }
}
