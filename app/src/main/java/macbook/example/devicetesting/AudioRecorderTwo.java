package macbook.example.devicetesting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;


public class AudioRecorderTwo extends AppCompatActivity
{
    Button b1,b2;
    MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder_two);

        mediaRecorder = new MediaRecorder();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {
                start();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void start(){

            try {
//                mediaRecorder = new MediaRecorder();
//                mediaRecorder.reset();

                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file = new File(path,"/aa.3gp");
//
//
//                java.io.File xmlFile = new java.io.File(Environment
//                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                        + "/Filename.xml");
                mediaRecorder.setOutputFile(file);
//                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

//                mediaRecorder.setOnErrorListener(errorListener);
//                mediaRecorder.setOnInfoListener(infoListener);

                mediaRecorder.prepare();
                mediaRecorder.start();

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }



    public void stop(){

        mediaRecorder.stop();
        mediaRecorder.release();

    }
}






