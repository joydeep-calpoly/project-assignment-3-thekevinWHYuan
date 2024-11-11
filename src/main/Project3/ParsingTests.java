package main.Project3;

import main.ParsingStuff.NewsAPIParser;
import main.ParsingStuff.ParseArticles;
import main.ParsingStuff.SimpleParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;


public class ParsingTests
{
    private static ArrayList<String> files = new ArrayList<>(Arrays.asList("project_3/inputs/small_news_api.json", "project_3/inputs/simple.json", "project_3/inputs/bad_simple.json", "project_3/inputs/bad_small_news_api.json"));
    private SimpleParser simpleStrategy = new SimpleParser(Logger.getLogger("test"));
    private NewsAPIParser newsAPIStrategy = new NewsAPIParser(Logger.getLogger("test"));
    private ParseArticles visitor = new ParseArticles();

    public ParsingTests() throws IOException {
    }


    /**
     * Tests a correctly formatted simple json. Should assert that there's only one article and that the article is of the title Assignment 2.
     * @throws IOException
     */
    @Test
    public void CorrectlySimpleFormattedTests() throws IOException {
        ArrayList<Article> formattedArticle = simpleStrategy.accept(visitor, files.get(1));
        Assert.assertEquals(1, formattedArticle.size());
        Assert.assertEquals(formattedArticle.getFirst().getTitle(), "Assignment #2");
    }

    /**
     * Tests an incorrectly formatted simple json. There shouldn't be a single article.
     * @throws IOException
     */
    @Test
    public void IncorrectlySimpleFormattedTests() throws IOException {
        ArrayList<Article> formattedArticle = simpleStrategy.accept(visitor, files.get(2));
        Assert.assertEquals(0, formattedArticle.size());
    }

    /**
     * Tests a correctly formatted NewsAPI json. There should be 2 articles each with the titles taken from the small_news_api.json.
     * @throws IOException
     */
    @Test
    public void CorrectlyNewsAPIFormattedTests() throws IOException {
        ArrayList<Article> formattedArticles = newsAPIStrategy.accept(visitor, files.get(0));
        Assert.assertEquals(2, formattedArticles.size());
        Assert.assertEquals(formattedArticles.getFirst().getTitle(), "The latest on the coronavirus pandemic and vaccines: Live updates - CNN");
        Assert.assertEquals(formattedArticles.get(1).getTitle(), "People line the streets of Boulder to honor slain police Officer Eric Talley - CNN");
    }

    /**
     * Tests an incorrectly formatted NewsAPI json. One of the fields from both articles in small_news_api.json have been removed and the list of
     * articles should be empty.
     * @throws IOException
     */
    @Test
    public void IncorrectlyNewsAPIFormattedTests() throws IOException {
        ArrayList<Article> formattedArticles = newsAPIStrategy.accept(visitor, files.get(3));
        Assert.assertEquals(0, formattedArticles.size());
    }

    /**
     * Tests to see if parser is consistent with input configuration. In this test, it should create a Simple parser that takes in a file name.
     * @throws IOException
     */
    @Test
    public void CreateSimpleParser() throws IOException {
        UserChoice userChoice = new UserChoice("f", "s", "simple.json");
        userChoice.parseUserInput();
        Assert.assertEquals("SimpleParser", userChoice.getCreatedParserClass());

    }

    /**
     * Tests to see if parser is consistent with input configuration. In this test, it should create a NewsAPI parser that takes in a file name.
     * @throws IOException
     */
    @Test
    public void CreateNewsAPIParser() throws IOException {
        UserChoice userChoice = new UserChoice("f", "n", "newsapi.json");
        userChoice.parseUserInput();
        Assert.assertEquals("NewsAPIParser", userChoice.getCreatedParserClass());

    }





}
