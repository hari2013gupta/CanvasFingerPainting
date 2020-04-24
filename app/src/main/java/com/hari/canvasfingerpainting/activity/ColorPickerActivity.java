package com.hari.canvasfingerpainting.activity;
  
import android.app.Activity;  
import android.content.Intent;
import android.graphics.Color;  
import android.os.Bundle;  
import android.view.View;  
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hari.canvasfingerpainting.ColorPicker;
import com.hari.canvasfingerpainting.R;

public class ColorPickerActivity extends Activity implements
        ColorPicker.OnColorChangedListener {
	
	Button btn2,btn3;
 /** Called when the activity is first created. */  
 @Override  
 public void onCreate(Bundle savedInstanceState) {  
  super.onCreate(savedInstanceState);  
  setContentView(R.layout.mainn);
  
  this.activity = this;  
  btn2=(Button)findViewById(R.id.button2);
  btn3=(Button)findViewById(R.id.button3);
  btn2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	    	 Intent intent=new Intent(getApplicationContext(), DrawingActivity.class);
//	    		i.putExtra("hari_ans", correct);
	    	 startActivity(intent);
	
		}
	});
  btn3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	    	 Intent intent=new Intent(getApplicationContext(), ColorPickerrActivity.class);
//	    		i.putExtra("hari_ans", correct);
	    	 startActivity(intent);
			
		}
	});

 }//endoncre   
 
//	Intent i=new Intent(getApplicationContext(), DrawingActivity.class);
//	i.putExtra("hari_ans", correct);
//	startActivity(i);
//	finish();

// ans= getIntent().getExtras().getInt("hari_ans");
//	System.out.println("-------------Answer----------//"+ans);

 @Override  
 public void colorChanged(String str,int color) {    
  ColorPickerActivity.this.findViewById(android.R.id.content)  
  .setBackgroundColor(color);  
 }  
  
 Activity activity;  
  
 public void getColor(View v) {  
  new ColorPicker(activity, this, "", Color.BLACK, Color.WHITE).show();   
 }
 
}   
