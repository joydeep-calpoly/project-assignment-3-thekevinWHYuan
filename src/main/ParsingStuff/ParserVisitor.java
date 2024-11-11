package main.ParsingStuff;

import main.Project3.Article;

import java.io.IOException;
import java.util.ArrayList;

interface ParserVisitor {

    public ArrayList<Article> visit(SimpleParser simpleParser, String jsonFile) throws IOException;
    public ArrayList<Article> visit(NewsAPIParser newsAPIParser, String jsonFile) throws IOException;

}
