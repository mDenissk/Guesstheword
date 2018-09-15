package den.hangman;

import java.util.Random;

/**
 * Created by Den on 23-May-17.
 */

public class Words {

    final Random rand = new Random();
    final String WordList[] =
    {
        "TABLE",
        "ANDROID",
        "AFTERNOON",
        "HANGMAN",
        "CARROT",
        "LAPTOP",
        "NOTEPAD",
        "CALCULATOR",
        "CALENDAR",
        "WINDOW",
        "WATCH",
        "COMPUTER",
        "ALPHABET",
        "BROWSER",
        "ORGANIZER",
        "NOTEBOOK",
        "TABLET",
        "CHAIR",
    };

    public String getWord()
    {
        return(WordList[rand.nextInt(WordList.length)]);
    }

}
