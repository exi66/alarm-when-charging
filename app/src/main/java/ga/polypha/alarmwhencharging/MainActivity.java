package ga.polypha.alarmwhencharging;


import static android.content.Intent.ACTION_POWER_CONNECTED;
import static android.content.Intent.ACTION_POWER_DISCONNECTED;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public Alarm alarm;
    public ChargingReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.alarm = new Alarm(this);
        this.receiver = new ChargingReceiver(alarm);

        setContentView(R.layout.activity_main);

        Button testSoundButton = (Button)findViewById(R.id.testSoundButton);
        testSoundButton.setOnClickListener(view -> alarm.playAlarm());

        Button stopAlarmButton = (Button)findViewById(R.id.stopAlarmButton);
        stopAlarmButton.setOnClickListener(view -> alarm.stopAlarm());

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch startEventHandler = (Switch)findViewById(R.id.startEventHandler);
        startEventHandler.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Context context = getApplicationContext();
            if (isChecked) {
                IntentFilter connected = new IntentFilter(ACTION_POWER_CONNECTED);
                IntentFilter disconnected = new IntentFilter(ACTION_POWER_DISCONNECTED);

                context.registerReceiver(receiver, connected);
                context.registerReceiver(receiver, disconnected);
            } else {
                context.unregisterReceiver(receiver);
            }
        });
    }
}