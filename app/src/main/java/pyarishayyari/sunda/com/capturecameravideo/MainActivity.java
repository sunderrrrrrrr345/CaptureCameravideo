package pyarishayyari.sunda.com.capturecameravideo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    VideoView videoView;
    Uri videoFileUri;
    Button captureVideoButton,playVideoButton,captureWithoutDataVideoButton;
    public static int VIDEO_CAPTURED = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captureVideoButton = (Button) this.findViewById(R.id.CaptureVideoButton);
        playVideoButton = (Button) this.findViewById(R.id.PlayVideoButton);
        captureWithoutDataVideoButton = (Button) this.findViewById(R.id.CaptureVideoWithoutDataButton);
        videoView = (VideoView) this.findViewById(R.id.VideoView);
        captureVideoButton.setOnClickListener(this);
        playVideoButton.setOnClickListener(this);
        captureWithoutDataVideoButton.setOnClickListener(this);
        playVideoButton.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if (v == captureVideoButton) {
            Intent captureVideoIntent =new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(captureVideoIntent,VIDEO_CAPTURED);
        }if (v == captureWithoutDataVideoButton) {
            playVideoButton.setEnabled(false);
            Intent captureVideoIntent =new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
            startActivity(captureVideoIntent);
        }else if (v == playVideoButton) {
            videoView.setVideoURI(videoFileUri);
            videoView.start();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (resultCode == RESULT_OK && requestCode == VIDEO_CAPTURED) {
            videoFileUri = data.getData();
            playVideoButton.setEnabled(true);
        }
    }
}
