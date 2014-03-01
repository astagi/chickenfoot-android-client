package org.as.chickenfoot;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private ControlClient client = new ControlClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(BuildConfig.DEBUG) {
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
        }
        initCommands();
        client.connect("192.168.0.6", 5005);
    }


    private void initCommands() {
    	((Button)this.findViewById(R.id.btn_up)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    client.fw();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                    client.stopBackMotor();
                    return true;
                }

                return false;
            }
        });
    	
    	((Button)this.findViewById(R.id.btn_down)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    client.rw();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                    client.stopBackMotor();
                    return true;
                }

                return false;
            }
        });
    	
    	((Button)this.findViewById(R.id.btn_left)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    client.rl();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                    client.stopFrontMotor();
                    return true;
                }

                return false;
            }
        });
    	
    	((Button)this.findViewById(R.id.btn_right)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    client.rr();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                    client.stopFrontMotor();
                    return true;
                }

                return false;
            }
        });
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
