package a13282.egco428.com.fortunecookie;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Suppanyu on 11/8/2016.
 */
public class Cookie extends AppCompatActivity implements SensorEventListener {
    SensorManager sersormanager;
    private ImageView cookies;
    private Button shake;
    private TextView result;
    private TextView date;
    private long lastupdate;
    private boolean isshake = true;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookie);
        cookies =(ImageView)findViewById(R.id.cookiesimage);
        shake =(Button)findViewById(R.id.Shakebtn);
        result =(TextView)findViewById(R.id.Resultoutput);
        date =(TextView)findViewById(R.id.Date);
        cookies.setImageResource(R.drawable.image1);

        int[] imageID ={R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7};
        String[] fortune ={"Result: You'll get A.","Result: You're lucky.","Result: Don't Panic.","Result: Something surprise you today.","Result: Work Harder","Something surprise you today"};
        int[] weight = {1,1,-1,1,-1,1};
        shake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shake.setText("Shaking");
                isshake = true;
            }
        });
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        float accelationSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = System.currentTimeMillis();
        if (accelationSquareRoot > 2 && accelationSquareRoot <=3 && isshake == true){
            if (actualTime - lastupdate < 200) {
                return;
            }
            lastupdate = actualTime;

            ImageView imgView = new ImageView(this);
            Random rand = new Random();
            int rndInt = rand.nextInt(n) + 1;
            String imgName = "img" + rndInt;
            int id = getResources().getIdentifier(imgName, "drawable", getPackageName());
            imgView.setImageResource(id);
        }
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.create,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()== R.id.back){
            startActivity(new Intent(Cookie.this,Main.class));
            return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}

