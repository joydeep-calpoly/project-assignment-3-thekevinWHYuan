package main.ParsingStuff;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.Project3.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

/**
 * Parser is an abstract class extended by the different types of parsers because all these
 * attributes are needed in each parser.
 */
public abstract class Parser{
    protected final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    protected FileHandler file;
    protected Logger log;
    protected SimpleFormatter formatter = new SimpleFormatter();

    Parser(Logger log) throws IOException {
        this.log = log;
        LogManager.getLogManager().reset();
        this.file = new FileHandler(".out");
        file.setLevel(Level.WARNING);
        log.addHandler(file);
        file.setFormatter(formatter);
    }

    public abstract ArrayList<Article> accept(ParserVisitor visitor, String jsonFile) throws IOException;


}
