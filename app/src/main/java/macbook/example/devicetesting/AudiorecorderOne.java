package macbook.example.devicetesting;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Magnifier;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AudiorecorderOne extends AppCompatActivity
{
    TextView tvRecordAudio,tvPlayArea,tvResult;
    Button btnStartRecording,btnStopRecording,btnPlayRecording,btnPauseRecording;
    String pathSave = "";
    File path ;
    File file;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    final int REQUEST_PERMISSION_CODE= 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);

        if(!checkPermissionFromDevice())
            requestPermission();

        tvRecordAudio = (TextView) findViewById(R.id.tvRecordAudio);
        tvPlayArea = (TextView) findViewById(R.id.tvPlayArea);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnStartRecording = (Button) findViewById(R.id.btnStartRecording);
        btnStopRecording = (Button) findViewById(R.id.btnStopRecording);
        btnPlayRecording = (Button) findViewById(R.id.btnPlayRecording);
        btnPauseRecording =(Button) findViewById(R.id.btnPauseRecording);

        mediaRecorder = new MediaRecorder();
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


        btnStartRecording.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v)
                {

                    if(checkPermissionFromDevice())
                    {

//                    pathSave = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"
//                            + UUID.randomUUID().toString()+"_audio_record.3gp";



                    try
                    {
                        setupMediaRecorder();
//                        mediaRecorder.prepare();
//                        mediaRecorder.start();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    btnPlayRecording.setEnabled(false);
                    btnPauseRecording.setEnabled(false);
                    btnStopRecording.setEnabled(true);
                    Toast.makeText(AudiorecorderOne.this,"Recording...",Toast.LENGTH_LONG).show();
                    tvResult.setText("Start Recording clicked");
                    }

                    else
                    {
                        requestPermission();
                    }


                }
            });

            btnStopRecording.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

//                    btnStopRecording.setBackground(#111111);

//                    mediaRecorder.stop();
                    if (mediaRecorder != null)
                    {
                        try
                        {
//                            mediaRecorder.stop();
                            mediaRecorder.stop();
                            mediaRecorder.release();

                            Toast.makeText(AudiorecorderOne.this,"not null",Toast.LENGTH_LONG).show();
                        }
                        catch(RuntimeException ex)
                        {
                            //Ignore
                        }
                    }
                    else
                    {
                        Toast.makeText(AudiorecorderOne.this,"Null",Toast.LENGTH_LONG).show();
                    }
                    btnStopRecording.setEnabled(false);
                    btnStartRecording.setEnabled(true);
                    btnPlayRecording.setEnabled(true);
                    btnPauseRecording.setEnabled(false);
                    tvResult.setText("Stop Recording clicked");
                }
            });

            btnPlayRecording.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    btnPauseRecording.setEnabled(true);
                    btnStopRecording.setEnabled(false);
                    btnStartRecording.setEnabled(false);

                    mediaPlayer = new MediaPlayer();
                    try
                    {
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                    Toast.makeText(AudiorecorderOne.this,"Playing...",Toast.LENGTH_LONG).show();
                    tvResult.setText("Play Recording clicked");

                }
            });

            btnPauseRecording.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v)
                {
                    btnStopRecording.setEnabled(false);
                    btnStartRecording.setEnabled(true);
                    btnPlayRecording.setEnabled(true);
                    btnPauseRecording.setEnabled(false);

                    if(mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
//                        setupMediaRecorder();
                        tvResult.setText("Pause Recording clicked");

                    }
                }
            });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupMediaRecorder() throws IOException {
//        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        file = new File(path,"/record.3gp");


//        mediaRecorder.setOutputFile(pathSave);
        mediaRecorder.setOutputFile(file);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.prepare();
        mediaRecorder.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
            switch (requestCode)
            {
                case REQUEST_PERMISSION_CODE:
                {
                    if(grantResults.length > 0)
                        Toast.makeText(AudiorecorderOne.this,"Permission Granted.",Toast.LENGTH_LONG).show();

                    else
                        Toast.makeText(AudiorecorderOne.this,"Permission Denied.",Toast.LENGTH_LONG).show();

                }
            }
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(AudiorecorderOne.this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        },REQUEST_PERMISSION_CODE);
    }

    private boolean checkPermissionFromDevice()
    {
        int write_external_storage_result = ContextCompat.checkSelfPermission(AudiorecorderOne.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(AudiorecorderOne.this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED ;

    }
}
