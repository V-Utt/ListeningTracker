package services;

import com.google.gson.*;
import models.Album;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.*;
import java.util.ArrayList;
import java.io.*;

public class ApiProcessor {
    private final HttpClient client;

    public ApiProcessor() {
        client = HttpClient.newHttpClient();
    }

    public ArrayList<Album> searchFunc(String apiReq) throws Exception {
        ArrayList<Album> results = new ArrayList<>();

        String encodedReq = URLEncoder.encode(apiReq, StandardCharsets.UTF_8);
        String completeUrl = "https://musicbrainz.org/ws/2/release-group/"
                + "?query=" + encodedReq + "%20AND%20primarytype:album"
                + "&fmt=json&limit=20";

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(completeUrl)).header("User-Agent", "ListeningTracker/1.0 (varun.utt07@gmail.com)").GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "MusicBrainz returned status " + response.statusCode()
            );
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonArray releases = json.getAsJsonArray("release-groups");

        for (JsonElement part : releases) {
            JsonObject release = part.getAsJsonObject();
            String id = release.get("id").getAsString();
            String title = release.get("title").getAsString();
            String date = "";

            if (release.has("first-release-date") && !release.get("first-release-date").isJsonNull()) {
                date = release.get("first-release-date").getAsString();
            }

            String year = "";
            if (date.length() >= 4) {
                year = date.substring(0, 4);
            }

            String artist = "";

            if (release.get("artist-credit") != null) {
                JsonArray credits = release.getAsJsonArray("artist-credit");

                StringBuilder artistBuilder = new StringBuilder();

                for (JsonElement credit : credits) {
                    if (!credit.isJsonObject()) {
                        continue;
                    }

                    JsonObject creditObject = credit.getAsJsonObject();

                    String artistName = "";

                    if (creditObject.has("name")
                            && !creditObject.get("name").isJsonNull()) {
                        artistName = creditObject.get("name").getAsString();
                    }

                    if (!artistName.isEmpty()) {
                        if (artistBuilder.length() > 0) {
                            artistBuilder.append(", ");
                        }

                        artistBuilder.append(artistName);
                    }
                }

                artist = artistBuilder.toString();
            }

            Album toCreate = new Album(id, title, artist, year);
            results.add(toCreate);
        }

        return results;

    }



}
