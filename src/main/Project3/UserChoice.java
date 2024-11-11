package main.Project3;

import main.ParsingStuff.ParseArticles;
import main.ParsingStuff.ParseFactory;
import main.ParsingStuff.Parser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class UserChoice {

    private static final ParseArticles visitor = new ParseArticles();

    // simple or newsapi
    private String format;

    // file or link
    private String source;

    // name of file or link endpoint
    private String sourceNameOrLink;

    /**
     * Returns the class type of the parser. Primarily used for testing.
     * @throws IOException
     */
    public String getCreatedParserClass() {
        return createdParser.getClass().getSimpleName();
    }

    private Parser createdParser;

    UserChoice(String source, String format, String sourceNameOrLink){
        this.source = source;
        this.format = format;
        this.sourceNameOrLink = sourceNameOrLink;
    }
    /**
     * Uses the given attributes and parses the link or file based on how the user wants it formattted.
     * NewsAPI link however will always result in a newsapi format.
     * @throws IOException
     */
    ArrayList<Article> parseUserInput() throws IOException {
        switch (source){
            case "l":
                writeJsonFromLink(sourceNameOrLink);
                this.sourceNameOrLink = "api.json";
                this.format = source;
        }
        this.createdParser = ParseFactory.createParser(format);
        return createdParser.accept(visitor, "./project_3/inputs/" + sourceNameOrLink);
    }

    /**
     * Because of how json is handled in this program, this function writes a json file from the response given
     * from the endpoint which will then be later parsed by a parser.
     * @param link: link to the newsapi endpoint (be sure to have an apikey)
     * @throws IOException
     */
    private static void writeJsonFromLink(String link) throws IOException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(link)).GET().build();
        FileWriter fileWriter = new FileWriter("./project_3/inputs/api.json");
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            fileWriter.write(response.body());
            fileWriter.close();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
