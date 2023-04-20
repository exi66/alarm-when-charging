package ga.polypha.alarmwhencharging;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import java.io.IOException;
import java.security.AccessControlContext;

public class Alarm {

    protected Context context;
    protected Uri alert;
    protected MediaPlayer mp;

    public Alarm(Context context) {
        this.context = context;
        this.alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (this.alert == null) {
            this.alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (this.alert == null) {
                this.alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
    }

    public void playAlarm() {
        if(this.mp != null && this.mp.isPlaying()){
            this.mp.stop();
        }
        this.mp = new MediaPlayer();
        try {
            this.mp.setDataSource(context, this.alert);

            if (Build.VERSION.SDK_INT >= 21) {
                this.mp.setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build());
            } else {
                this.mp.setAudioStreamType(AudioManager.STREAM_ALARM);
            }
            this.mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mp.start();
    }

    public void stopAlarm() {
        if(this.mp != null && this.mp.isPlaying()){
            this.mp.stop();
        }
    }
}
