package services;

import com.google.gson.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.util.ArrayList;

public class ApiProcessor {
    private final HttpClient client;

    public MusicBrainzService() {
        client = HttpClient.newHttpClient();
    }

    public ArrayList<Album> searchFunc(String apiReq) {
        ArrayList<Album> results = new ArrayList<>();
    }
}
