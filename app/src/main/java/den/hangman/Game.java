package den.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    final private int Pictures[] =
    {
        R.drawable.frame_00,
        R.drawable.frame_01,
        R.drawable.frame_02,
        R.drawable.frame_03,
        R.drawable.frame_04,
        R.drawable.frame_05,
        R.drawable.frame_06,
        R.drawable.frame_07,
        R.drawable.frame_08,
        R.drawable.frame_09,
        R.drawable.frame_10,
        R.drawable.frame_11,
        R.drawable.frame_12
    };

    final private int CheckBoxes[] =
    {
        R.id.checkBoxA,
        R.id.checkBoxB,
        R.id.checkBoxC,
        R.id.checkBoxD,
        R.id.checkBoxE,
        R.id.checkBoxF,
        R.id.checkBoxG,
        R.id.checkBoxH,
        R.id.checkBoxI,
        R.id.checkBoxJ,
        R.id.checkBoxK,
        R.id.checkBoxL,
        R.id.checkBoxM,
        R.id.checkBoxN,
        R.id.checkBoxO,
        R.id.checkBoxP,
        R.id.checkBoxQ,
        R.id.checkBoxR,
        R.id.checkBoxS,
        R.id.checkBoxT,
        R.id.checkBoxU,
        R.id.checkBoxV,
        R.id.checkBoxW,
        R.id.checkBoxX,
        R.id.checkBoxY,
        R.id.checkBoxZ
    };

    private TextView text_field;
    private ImageView image;
    private String LettersUsed;
    private int pictureNr;


    private String guessWord;
    private Words words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        text_field = (TextView) findViewById(R.id.textInfo);
        image = (ImageView) findViewById(R.id.visualAnswer);
        words = new Words(); // class with words
        newGame();
    }

    public void addLetter(View view)
    {
        view.setEnabled(false);
        LettersUsed = LettersUsed + ((CheckBox) view).getText().toString();
        update();
    }

    public void PressNewGame(View view)
    {
        newGame();
    }

    public void newGame()
    {
        prepareCheckboxes();
        pictureNr = 0;
        image.setImageResource(Pictures[pictureNr]);
        LettersUsed = "";
        guessWord = words.getWord(); // get random word from class
        update();
    }

    public void update()
    {
        char newLine[] = new char[guessWord.length()];
        char gw[] = guessWord.toCharArray();
        char lu[] = LettersUsed.toCharArray();
        char c ;

        for (int i = 0; i < gw.length ; i++) // check if used letters are in word
        {
            c = '_'; // if 'i' letter wasn't guessed then it is hidden
            for(int j = 0 ; j < lu.length ; j++ )
            {
                if (gw[i] == lu[j])
                {
                    c = lu[j]; // if 'i' letter was guessed then it isn't hidden
                    break;
                }
            }
            newLine[i] = c; // add guessed or not guessed letter
        }

        String line = String.valueOf(newLine);
        text_field.setText(line);

        if (lu.length > 0) // if there are guessed letters
        {
            if(!line.contains(String.valueOf(lu[lu.length-1]))) // if new letter is not in the word
            {
                pictureNr++;
                image.setImageResource(Pictures[pictureNr]);
                if ((pictureNr+2) == (Pictures.length)) // lifes depends from (frames-1) (-1 because the last frame is "win frame")
                {
                    lose();
                }
            }
            else if (!line.contains("_")) // if there aren't did not guess letters
            {
                win();
            }
        }
    }

    public void win()
    {
        endGame();
        image.setImageResource(Pictures[12]); // frame with happy human
    }

    public void lose()
    {
        endGame();
        text_field.setText(guessWord); // show word
    }

    public void prepareCheckboxes()
    {

        for (int i = 0 ;  i < CheckBoxes.length ; i++)
        {
            findViewById(CheckBoxes[i]).setEnabled(true);
            ((CheckBox) findViewById(CheckBoxes[i])).setChecked(false);
        }
    }

    public void endGame()
    {
        for (int i = 0 ;  i < CheckBoxes.length ; i++)
        {
             findViewById(CheckBoxes[i]).setEnabled(false);
        }
    }
}
