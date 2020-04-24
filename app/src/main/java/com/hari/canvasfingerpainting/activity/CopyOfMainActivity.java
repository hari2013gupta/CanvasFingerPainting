package com.hari.canvasfingerpainting.activity;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.hari.canvasfingerpainting.R;

public class CopyOfMainActivity extends Activity {
    Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mmain);
		Thread timer = new Thread(){
			public void run(){
			try{
				sleep(5000);
			}catch(InterruptedException e){
			}finally{
				Intent openMenu= new Intent(getApplicationContext(), MainActivity.class);
				System.out.println("---------ok---------");
				startActivity(openMenu);
				finish();
			}

		    }
		};
		
		timer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
