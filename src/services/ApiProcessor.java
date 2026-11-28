package services;

import com.google.gson.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.*;
import java.util.ArrayList;
import java.io.*;

public class ApiProcessor {
    private final HttpClient client;

    public MusicBrainzService() {
        client = HttpClient.newHttpClient();
    }

    public ArrayList<Album> searchFunc(String apiReq) throws Exception {
        ArrayList<Album> results = new ArrayList<>();

        String encodedReq = URLEncoder.encode(query, StandardCharsets.UTF_8);
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



    }



}
