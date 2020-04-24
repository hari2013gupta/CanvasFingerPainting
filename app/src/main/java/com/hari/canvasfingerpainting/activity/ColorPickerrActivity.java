package com.hari.canvasfingerpainting.activity;
import android.app.Activity;  
import android.graphics.Color;  
import android.os.Bundle;  
import android.view.View;

import com.hari.canvasfingerpainting.ColorPickerr;
import com.hari.canvasfingerpainting.R;

public class ColorPickerrActivity extends Activity implements
        ColorPickerr.OnColorChangedListener {
 /** Called when the activity is first created. */  
 @Override  
 public void onCreate(Bundle savedInstanceState) {  
  super.onCreate(savedInstanceState);  
  setContentView(R.layout.mainn);
  this.activity = this;  
 }   
  
 @Override  
 public void colorChanged(int color) {    
  ColorPickerrActivity.this.findViewById(android.R.id.content)  
  .setBackgroundColor(color);  
 }  
  
 Activity activity;  
  
 public void getColor(View v) {  
  new ColorPickerr(activity, ColorPickerrActivity.this, Color.WHITE)  
    .show();    
 }  
} 
