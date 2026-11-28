package utilities;

import models.Album;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.nio.file.*;

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

                Album album = new Album(decode(pathSplit[0]), decode(pathSplit[1]), decode(pathSplit[2]), decode(pathSplit[3]));

                album.setRating(Integer.parseInt(pathSplit[4]));
                album.setUserStat(decode(pathSplit[5]));

                albumList.add(album);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return albumList;
    }

    public static void save(ArrayList<Album> albums) {
        try {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (Album album : albums) {
                    writer.write(
                            encode(album.getId()) + "\t" +
                                    encode(album.getName()) + "\t" +
                                    encode(album.getArtist()) + "\t" +
                                    encode(album.getYear()) + "\t" +
                                    album.getRating() + "\t" +
                                    encode(album.getUserStat())
                    );
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
