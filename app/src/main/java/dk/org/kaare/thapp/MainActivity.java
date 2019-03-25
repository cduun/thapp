package dk.org.kaare.thapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    WorldRotation wr;
    TextView xValue;
    TextView yValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = findViewById(R.id.xAxisValue);
        yValue = findViewById(R.id.yAxisValue);

        this.wr = new WorldRotation(this, xValue, yValue);
        wr.registerListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        wr.registerListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wr.unregisterListener();
    }

}
