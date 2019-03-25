package dk.org.kaare.thapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

/**
 * This class is used for integrating with the gyroscope hardware
 */
public class Gyro implements SensorEventListener {

    Context mContext;
    TextView xValue;
    TextView yValue;
    private float[] mGeomagnetic = new float[3];
    private float[] mGravity = new float[3];
    private float R[] = new float[9];
    private float I[] = new float[9];
    private float remapCoords[] = new float[9];
    private float orientation[] = new float[3];
    private SensorManager sensorManager;
    private Sensor acc;
    private Sensor mac;

    public Gyro(Context mContext, TextView xValue, TextView yValue) {
        this.mContext = mContext;
        this.xValue = xValue;
        this.yValue = yValue;
        this.sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        this.acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.mac = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;

        if (mGravity != null && mGeomagnetic != null) {

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_X, SensorManager.AXIS_Y, remapCoords);
            if (success) {
                SensorManager.getOrientation(remapCoords, orientation);

                Log.d("x", Double.toString(Math.toDegrees( orientation[1] )));
                Log.d("y", Double.toString(Math.toDegrees( orientation[2] )));
                Log.d("z", Double.toString(Math.toDegrees( orientation[0] )));

                this.xValue.setText(Double.toString(Math.toDegrees( orientation[1])));
                this.yValue.setText(Double.toString(Math.toDegrees( orientation[2])));
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // nothing yet
    }


    public void registerListener() {
        sensorManager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mac, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener() {
        sensorManager.unregisterListener(this);
    }

}
