package ga.polypha.alarmwhencharging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ChargingReceiver extends BroadcastReceiver {

    protected Alarm alarm;

    public ChargingReceiver() {
        this.alarm = null;
    }

    public ChargingReceiver(Alarm alarm) {
        this.alarm = alarm;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.alarm == null) return;

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
            alarm.playAlarm();
        } else if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {

        }
    }
}
