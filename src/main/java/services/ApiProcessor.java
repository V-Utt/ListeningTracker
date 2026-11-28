package services;

import com.google.gson.*;
import models.Album;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.*;
import java.util.ArrayList;
import java.io.*;
import org.json.*;

public class ApiProcessor {
    private final HttpClient client;

    public ApiProcessor() {
        client = HttpClient.newHttpClient();
    }

    public ArrayList<Album> searchFunc(String apiReq) throws Exception {
        ArrayList<Album> results = new ArrayList<>();

        String encodedReq = URLEncoder.encode(apiReq, StandardCharsets.UTF_8);
        String completeUrl = "https://musicbrainz.org/ws/2/release/"
                + "?query=" + encodedReq
                + "&fmt=json&limit=20";

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(completeUrl)).header("User", "ListeningTracker/1.0 (music stats app)").GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "MusicBrainz returned status " + response.statusCode()
            );
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonArray releases = json.getAsJsonArray("releases");

        for (JsonElement part : releases) {
            JsonObject release = element.getAsJsonObject();
            String id = release.get("id").getAsString();
            String title = release.get("title").getAsString();
            String date = release.get("date").getAsString(); //potential error here with "date"

            String year = "";
            if (date.length() >= 4) {
                year = date.substring(0, 4);
            }

            String artist = "";

            if (release.get("artist") != null) {
                artist = release.get("artist").getAsString();
            }

            Album toCreate = new Album(id, title, artist, year);
            results.add(toCreate);
        }

        return results;

    }



}
