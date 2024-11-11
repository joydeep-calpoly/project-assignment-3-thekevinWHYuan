package main.ParsingStuff;

import main.Project3.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class NewsAPIParser extends Parser {

    public NewsAPIParser(Logger log) throws IOException{
        super(log);
    }


    /**
     * Returns a list of articles parsed from the NewsAPI JSON format.
     * This method uses the visitor pattern in order to parse given json files. In this context,
     * it passes in itself which is the newsapiparser which uses the method corresponding to the NewsAPI format.
     * @param visitor the visitor with different types of parsing depending on what parser is passed in
     * @param jsonFile the name of the json file
     * @return         a list of articles or an empty list
     */
    public ArrayList<Article> accept(ParserVisitor visitor, String jsonFile) throws IOException {
        return visitor.visit(this, jsonFile);
    }

}