package com.github.arlekinside.movie.services.api;

import com.github.arlekinside.movie.models.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ImbdApiService {

    private final String key;
    private StringBuffer urlBuffer;
    private OkHttpClient client;
    private Gson gson;
    private String url;

    @Autowired
    public ImbdApiService(@Value("${imbd.api.key}") String key, @Value("${imbd.api.url}") String url) {
        this.key = key;
        this.url = url;
        gson = new Gson();
        client = new OkHttpClient().newBuilder()
                .build();
    }

    public List<Movie> find(String name, String lang) throws IOException {
        urlBuffer = new StringBuffer();
        urlBuffer.append(url)
                .append(lang)
                .append("/API/SearchMovie/")
                .append(key)
                .append('/')
                .append(name);
        Request request = new Request.Builder()
                .url(urlBuffer.toString())
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String temp = Objects.requireNonNull(response.body()).string();
        SearchResult result = gson.fromJson(
                temp,
                new TypeToken<SearchResult>() {
                }.getType()
        );
        return result.getResults();
    }

    private class SearchResult {

        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }

        public void setResults(List<Movie> results) {
            this.results = results;
        }
    }
}
