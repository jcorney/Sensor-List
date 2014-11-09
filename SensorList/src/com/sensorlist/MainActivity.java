package com.sensorlist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView mySensor;
	String myString[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mySensor = (TextView) this.findViewById(R.id.textSensorList);

		SensorManager mgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensors = mgr.getSensorList(Sensor.TYPE_ALL);

		for (Sensor sensor : sensors) {
			mySensor.append(sensor.getName() + "\n");

		}

		try {
			FileOutputStream mySensorListFile = openFileOutput("MyTest.txt",
					Context.MODE_WORLD_READABLE);
			OutputStreamWriter myOutputFile = new OutputStreamWriter(
					mySensorListFile);

			String mySensorString = mySensor.getText().toString();
			myOutputFile.write(mySensorString);

			myOutputFile.flush();
			myOutputFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
