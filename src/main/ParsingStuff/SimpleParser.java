package main.ParsingStuff;

import main.Project3.Article;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SimpleParser extends Parser{

    public SimpleParser(Logger log) throws IOException{
        super(log);
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss."));
    }

    /**
     * Returns an article in a list parsed from the simple JSON format.
     * This method uses the visitor pattern in order to parse given json files. In this context,
     * it passes in itself which is the simpleparser which uses the method corresponding to the simple format.
     * @param visitor the visitor with different types of parsing depending on what parser is passed in
     * @param jsonFile the name of the json file
     * @return         the article in a list or an empty list
     */

    @Override
    public ArrayList<Article> accept(ParserVisitor visitor, String jsonFile) throws IOException{
        return visitor.visit(this, jsonFile);
    }
}
