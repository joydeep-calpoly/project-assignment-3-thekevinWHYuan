package main.Project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Result {

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return (ArrayList<Article>) articles.clone();
    }

    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

    @JsonCreator
    public Result(@JsonProperty("status") String status,@JsonProperty("totalResults") int totalResults, @JsonProperty("articles") ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = (ArrayList<Article>)articles.clone();
    }
}
