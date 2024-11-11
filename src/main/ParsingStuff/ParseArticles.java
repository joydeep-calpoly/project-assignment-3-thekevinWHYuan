package main.ParsingStuff;

import main.Project3.Article;
import main.Project3.Result;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ParseArticles implements ParserVisitor {

    /**
     * Returns a list of news articles parsed from the simple JSON format.
     * This method parses a jsonFile and extracts the single article from it. A badly formatted article that have missing fields will return an empty list.
     * Because of the way parseArticle has been written, it returns a list with a single article instead of just an article object.
     * A badly formatted article as well will be written to a .out file.
     * @param jsonFile the name of the json file
     * @param simpleParser specific parser meant to parse Simple format
     * @return         the article in a list or an empty list
     */
    @Override
    public ArrayList<Article> visit(SimpleParser simpleParser, String jsonFile) throws IOException {
        ArrayList<Article> articles = new ArrayList<>();
        Article value = simpleParser.mapper.readValue(new File(jsonFile), Article.class);
        if (value.getTitle() == null || value.getDescription() == null || value.getUrl() == null || value.getPublishedAt() == null) {
            simpleParser.log.log(Level.WARNING, "Article formatted incorrectly. Missing: " +
                    (value.getTitle() == null ? "Title" : "") +
                    (value.getDescription() == null ? ", Description" : "") +
                    (value.getUrl() == null ? ", Url" : "") +
                    (value.getPublishedAt() == null ? ", Published Date Time" : ""));
        }
        else{
            articles.add(value);
        }
        return articles;
    }

    /**
     * Returns a list of news articles parsed from the NewsAPI JSON
     * This method parses an jsonFile and extracts the articles from the file. Badly formatted articles that have missing fields are ignored
     * and not added to the list that is to be returned. It is possible that all articles are badly formatted and can return and empty list.
     * Any badly formatted articles as well will be written to a .out file. A get request to the newsapi endpoint will be written to file and
     * uses this parser to parse the json as well.
     * @param jsonFile the name of the json file
     * @param newsAPIParser specific parser meant to parse NewsAPI format
     * @return         the list of properly formatted articles parsed from the json file
     */
    @Override
    public ArrayList<Article> visit(NewsAPIParser newsAPIParser, String jsonFile) throws IOException {
        ArrayList<Article> articles = new ArrayList<>();
        Result value = newsAPIParser.mapper.readValue(new File(jsonFile), Result.class);
        for (Article article: value.getArticles()){
            if (article.getTitle() == null || article.getDescription() == null || article.getUrl() == null || article.getPublishedAt() == null) {
                newsAPIParser.log.log(Level.WARNING, "Article formatted incorrectly. Missing: " +
                        (article.getTitle() == null ? "Title" : "") +
                        (article.getDescription() == null ? ", Description" : "") +
                        (article.getUrl() == null ? ", Url" : "") +
                        (article.getPublishedAt() == null ? ", Published Date Time" : ""));
                continue;
            }
            articles.add(article);
        }
        return articles;
    }




}
