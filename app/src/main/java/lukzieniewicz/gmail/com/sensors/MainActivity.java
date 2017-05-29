package lukzieniewicz.gmail.com.sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sensor> sensors;
    private List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager s =(SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = s.getSensorList(Sensor.TYPE_ALL);

        initialize();
    }

    private void initialize(){
        setStrings();
        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), SensorDetailActivity.class);
                i.putExtra("index", position);
                startActivity(i);
            }
        });
    }

    private void setStrings(){
        strings = new LinkedList<String>();
        for(Sensor s : sensors){
            strings.add(s.getStringType());
        }
    }

    public void onCompassClick(View view){
        Intent i = new Intent(this, Compass.class);
        startActivity(i);
    }

    public void onGameClick(View view){
        Intent i = new Intent(this, WorkoutActivity.class);
        startActivity(i);
    }
}
