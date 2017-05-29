package lukzieniewicz.gmail.com.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SensorDetailActivity extends AppCompatActivity {

    private Sensor sensor;
    private SensorEventListener listener;
    private long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_detail);

        populate();
    }

    private void populate(){
        TextView title = (TextView) findViewById(R.id.sensor_title);


        int index = getIntent().getIntExtra("index", 999);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getSensorList(Sensor.TYPE_ALL).get(index);
        listener = new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                String str = "";
                for(float f : sensorEvent.values)
                    str += f + "\n";
                str += "accuracy: " + sensorEvent.accuracy + "\n";
                str += "time: " + (sensorEvent.timestamp - timestamp) + "\n";
                str += "resolution: " + sensor.getResolution();
                ((TextView)findViewById(R.id.sensor_details)).setText(str);
                timestamp = sensorEvent.timestamp;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        title.setText(sensor.getStringType());
    }

    public void onResolutionClick(View view){
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        manager.unregisterListener(listener);
        switch (view.getId()){
            case R.id.low:
                manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.medium:
                manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
                break;
            case R.id.high:
                manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
                break;
        }
    }


}
