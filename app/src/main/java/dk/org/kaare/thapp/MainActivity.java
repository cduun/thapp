package dk.org.kaare.thapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Gyro gyro;
    TextView xValue;
    TextView yValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = findViewById(R.id.xAxisValue);
        yValue = findViewById(R.id.yAxisValue);

        this.gyro = new Gyro(this, xValue, yValue);
        gyro.registerListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        gyro.registerListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyro.unregisterListener();
    }

}
