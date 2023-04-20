package ga.polypha.alarmwhencharging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ChargingReceiver extends BroadcastReceiver {

    protected Alarm alram;

    public ChargingReceiver(Alarm alarm) {
        this.alram = alarm;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
            alram.playAlarm();
        }
        else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {

        }
    }
}
