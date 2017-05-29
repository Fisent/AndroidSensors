package lukzieniewicz.gmail.com.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity implements SensorEventListener
{

    private Sensor sensor;
    private SensorManager manager;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onStart(){
        super.onStart();
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop(){
        super.onStop();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if(sensorEvent.values[0] > 10) {
            counter ++;
        }

        updateUI();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    private void updateUI(){
        ((TextView)findViewById(R.id.workout_text_view)).setText(counter + "");
    }
}
