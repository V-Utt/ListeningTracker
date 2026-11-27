package utilities;

import models.Album;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

public class FileManager {
    private static final String filePath = "data/albums.txt";

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(
                value.getBytes()
        );
    }

    private static String decode(String value) {
        return new String(Base64.getDecoder().decode(value));
    }

    public static ArrayList<Album> load() {
        ArrayList<Album> albumList = new ArrayList<>();

        File file = new File(filePath);

        if (file.exists() == false) {
            return albumList;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] pathSplit = line.split("\t", -1);

                if (pathSplit.length != 6) {
                    continue;
                }

                Album album = new Album(decode(pathSplit[0], pathSplit[1], pathSplit[2], pathSplit[3]));

                album.setRating(Integer.parseInt(pathSplit[4]));
                album.setStatus(decode(pathSplit[5]));

                albumList.add(album);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return albumList;
    }

}
