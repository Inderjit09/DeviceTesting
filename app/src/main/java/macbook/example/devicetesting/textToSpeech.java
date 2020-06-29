package macbook.example.devicetesting;

import androidx.appcompat.app.AppCompatActivity;
import android.speech.tts.TextToSpeech;
//        import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class textToSpeech extends AppCompatActivity
{
    SeekBar pitchBar,speedBar;
    TextToSpeech textToSpeech;
    EditText inputBox;
    Button btnSayit;

   @Override
    protected void onCreate(Bundle savedInstanceState)
   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        inputBox = findViewById(R.id.edittext1);
        pitchBar = findViewById(R.id.pitch1);
        speedBar = findViewById(R.id.speed1);
        btnSayit = (Button) findViewById(R.id.btnsayit);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA ||result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(textToSpeech.this,"Please speak English.",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        btnSayit.setEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(textToSpeech.this,"Failed.",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSayit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                speakText();
            }
        });
    }

    private void speakText()
    {
        String text = inputBox.getText().toString();

        float pitch = (float) pitchBar.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;

        float speed = (float) speedBar.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy()
    {
        if (textToSpeech != null) {
        textToSpeech.stop();
        textToSpeech.shutdown(); }
        super.onDestroy();
    }
}