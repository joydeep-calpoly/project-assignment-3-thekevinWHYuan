package main.ParsingStuff;

import java.io.IOException;
import java.util.logging.Logger;

public class ParseFactory {

    /**
     * Creates a parser based on the type of parser that's specified in the parameter. This utilizes
     * factory design pattern.
     * @param type: the type of parser that's needed denoted by a single character.
     * @throws IOException
     */
    public static Parser createParser(String type) throws IOException {
        if (type.equals("l") || type.equals("n")){
            return new NewsAPIParser(Logger.getLogger("test"));
        }
        else if (type.equals("s")){
            return new SimpleParser(Logger.getLogger("test"));
        }
        else{
            System.out.println("Incorrect types");
        }
        return null;
    }

}
