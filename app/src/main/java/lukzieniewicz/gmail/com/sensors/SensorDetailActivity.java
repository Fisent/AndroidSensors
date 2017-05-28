package lukzieniewicz.gmail.com.sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorDetailActivity extends AppCompatActivity {

    private Sensor sensor;

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
        Sensor s = sm.getSensorList(Sensor.TYPE_ALL).get(index);

        switch (s.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                title.setText("akcelerometr");
                break;
            case Sensor.TYPE_TEMPERATURE:
                title.setText("temperatura");
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                title.setText("geomagmetic");
                break;
            default:
                title.setText("aaa");
        }
    }
}
