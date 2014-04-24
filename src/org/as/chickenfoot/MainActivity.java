package org.as.chickenfoot;

import org.as.chickenfoot.client.ControlClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

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
    	((ImageButton)this.findViewById(R.id.btn_up)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                	((ImageButton)v).setImageResource(R.drawable.up_on);
                    client.fw();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                	((ImageButton)v).setImageResource(R.drawable.up);
                    client.stopBackMotor();
                    return true;
                }

                return true;
            }
        });
    	
    	((ImageButton)this.findViewById(R.id.btn_down)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                	((ImageButton)v).setImageResource(R.drawable.up_on);
                    client.rw();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                	((ImageButton)v).setImageResource(R.drawable.up);
                    client.stopBackMotor();
                    return true;
                }

                return true;
            }
        });
    	
    	((ImageButton)this.findViewById(R.id.btn_left)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                	((ImageButton)v).setImageResource(R.drawable.up_on);
                    client.rl();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                	((ImageButton)v).setImageResource(R.drawable.up);
                    client.stopFrontMotor();
                    return true;
                }

                return true;
            }
        });
    	
    	((ImageButton)this.findViewById(R.id.btn_right)).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                	((ImageButton)v).setImageResource(R.drawable.up_on);
                    client.rr();
                    return true;
                }
                
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                	((ImageButton)v).setImageResource(R.drawable.up);
                    client.stopFrontMotor();
                    return true;
                }

                return true;
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
