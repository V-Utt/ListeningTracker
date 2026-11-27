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

    public void updateRating(String id, int rating) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(id)) {
                albums.get(i).setRating(rating);
                FileManager.save(albums);
                return;
            }
        }
    }

    public void updateUserStat(String id, String userStat) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(id)) {
                albums.get(i).setUserStat(userStat);
                FileManager.save(albums);
                return;
            }
        }
    }
}
