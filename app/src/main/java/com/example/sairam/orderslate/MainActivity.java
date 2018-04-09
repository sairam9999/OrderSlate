package com.example.sairam.orderslate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView mImgView;
    Timer mContinuousTransitionDrawableTimer;
    TransitionDrawable transition;
    boolean isForward = true;
   // Context con = this.getApplicationContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleMenu circleMenu =  findViewById(R.id.circle_menu);

        final Context con = this.getApplicationContext();

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.mipmap.icon_home)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.icon_search)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.icon_notify)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.icon_setting)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                       // Toast.makeText(con, "abc"+index,Toast.LENGTH_LONG).show();

                       Log.d("sairam","####"+index);

                        Intent homeIntent = new Intent(con, HomeActivity.class);
                        Intent searchIntent = new Intent(con, SearchActivity.class);
                        Intent navigationIntent = new Intent(con, NavigationActivity.class);
                        Intent notificationIntent = new Intent(con, NotificationActivity.class);
                        Intent settingIntent = new Intent(con, SettingsActivity.class);

//                        EditText editText = (EditText) findViewById(R.id.editText);
//                        String message = editText.getText().toString();
//                        intent.putExtra(EXTRA_MESSAGE, message);
                        switch(index){
                            case 0:
                                startActivity(homeIntent);
                                break;
                            case 1:
                                startActivity(searchIntent);
                                break;
                            case 2:
                                startActivity(navigationIntent);
                                break;
                            case 3:
                                startActivity(notificationIntent);
                                break;
                            case 4:
                                startActivity(settingIntent);
                                break;
                        }


                    }

                });//.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

          /*  @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {}
*/
     //   });
        VideoView videoView =(VideoView)findViewById(R.id.videoView1);

        //Creating MediaController
        MediaController mediaController;
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri;
        Log.d("Sai",Environment.getExternalStorageDirectory().getPath()+ "video/small.mp4");
        //specify the location of media file
//        uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "video/small.mp4");
//        findViewById(R.id.video.small);
         uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.small1);
        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
    public class ContinuousTransitionDrawableTimerTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (isForward){
                        isForward = false;
                        transition.startTransition(5000);
                    }else{
                        isForward = true;
                        transition.reverseTransition(5000);
                    }
                }
            });
        }
    }
}

