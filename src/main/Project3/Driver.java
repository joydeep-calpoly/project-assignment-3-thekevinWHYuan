package main.Project3;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    /**
     * Gets user input on the type of format and whether they're trying to parse json from a file or link
     * This will then be passed on the userChoice class where it's parsed and returns a list of articles which will
     * be printed out.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Are you trying to parse a file, from a link or exit? (f/l/e)");
        String source = userInput.nextLine();
        String format = "";
        String sourceName = "";
        switch (source){
            case "f":
                System.out.println("What's the file name?");
                sourceName = userInput.nextLine();
                System.out.println("Is it in simple or newsapi format? (s/n)");
                format = userInput.nextLine();
                break;
            case "l":
                System.out.println("What's the newsapi link");
                sourceName = userInput.nextLine();
                break;
            case "e":
                return;
        }

        UserChoice userChoice = new UserChoice(source, format, sourceName);
        ArrayList<Article> articlesList = userChoice.parseUserInput();
        for (Article articles: articlesList){
            System.out.println(articles.getTitle() + '\n' + articles.getDescription() + '\n' + articles.getPublishedAt() + '\n' + articles.getUrl() + '\n');
        }

    }

}
