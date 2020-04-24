/***rock
 * hari_activity main
 * exteds color picker activity
 * 3001141300
 * **********************/
package com.hari.canvasfingerpainting.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hari.canvasfingerpainting.ColorPicker;
import com.hari.canvasfingerpainting.MyDrawView;
import com.hari.canvasfingerpainting.R;

public class DrawingActivity extends Activity implements
        ColorPicker.OnColorChangedListener {
    MyDrawView myDrawView,draw;
    Button _btn_save, _btn_capture;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {

        super.onCreate(savedInstanceState);
        myDrawView = new MyDrawView(this, null);
        setContentView(R.layout.activity_main);

        this.activity = this;  
        draw = (MyDrawView)findViewById(R.id.draw);    
        _btn_capture = (Button)findViewById(R.id.btn_capture);    
        _btn_save = (Button)findViewById(R.id.btn_save);    

        _btn_capture.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v)
            {
       		 try {
 	    		Intent myIntent = new Intent(DrawingActivity.this, 
 	    				ColorPickerActivity.class);
 	    		startActivity(myIntent);
//                 CaptureScreen();
//       			 save_sdcard();
  
       		 } catch (Exception e) {
                 // TODO: handle exception
                 e.printStackTrace();
             }
            	
            }
        });
            _btn_save.setOnClickListener(new View.OnClickListener() 
            {
            public void onClick(View v)
            {       
                //View content = myDrawView;

                //System.out.println(content);

                //Bitmap bitmap = content.getDrawingCache();

                File folder = new File(Environment.getExternalStorageDirectory().toString());
                 boolean success = false;
                 if (!folder.exists()) 
                 {
                     success = folder.mkdirs();
                 }

                 System.out.println(success+"folder");

                 File file = new File(Environment.getExternalStorageDirectory().toString() + "/sample.JPEG");

             if ( !file.exists() )
             {
                   try {
                    success = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
             }

             System.out.println(success+"file");

             FileOutputStream ostream = null;
                try
                {
                ostream = new FileOutputStream(file);

                System.out.println(ostream);

                Bitmap save = myDrawView.getBitmap();
                if(save == null) {
                    System.out.println("NULL bitmap save\n");
                }
                save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                //bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                   ostream.flush();
                    ostream.close();
                }catch (NullPointerException e) 
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Null error", Toast.LENGTH_SHORT).show();
                }

                catch (FileNotFoundException e) 
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "File error", Toast.LENGTH_SHORT).show();
                }

                catch (IOException e) 
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "IO error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        
    }

//	public void CaptureScreen() 
//	{
//		
//	SnapshotReadyCallback callback = new SnapshotReadyCallback() {
//
//		Bitmap bitmap;
//
//				@Override
//				public void onSnapshotReady(Bitmap arg0) {
//					// TODO Auto-generated method stub
//					bitmap = arg0;
//	                try {
//	                	File sdCard = Environment.getExternalStorageDirectory();
//	        			File dir = new File (sdCard.getAbsolutePath() + "/images_folder");
//	        			dir.mkdirs();
//	        			File file = new File(dir,"MyMapScreen" + System.currentTimeMillis()+".png");
//	                    //FileOutputStream out = new FileOutputStream("/mnt/sdcard/newimagesfold"
//	                      //  + "MyMapScreen" + System.currentTimeMillis()
//	                        //+ ".png");
//	        			FileOutputStream outStream;
//	        			 outStream = new FileOutputStream(file);
//	                    // above "/mnt ..... png" => is a storage path (where image will be stored) + name of image you can customize as per your Requirement
//
//	                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream);
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//				}
//	        };

//	        map.snapshot(callback);

	        // myMap is object of GoogleMap +> GoogleMap myMap;
	        // which is initialized in onCreate() => 
	        // myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_pass_home_call)).getMap();
//	}
//public void save_sdcard()
//{
//				System.out.println("--------i----//");
//				BitmapDrawable drawable = (BitmapDrawable) draw.getDrawable();
//				System.out.println("--------i----//");
//			    Bitmap bitmap = drawable.getBitmap();
//			  //File sdCardDirectory = Environment.getExternalStorageDirectory();
//			//File image = new File(sdCardDirectory, "bg6.jpg");
//			File sdCard = Environment.getExternalStorageDirectory();
//			File dir = new File (sdCard.getAbsolutePath() + "/imagesfolder");
//			dir.mkdirs();
//			File file = new File(dir, images[i]);
//			boolean success = false;
//		    // Encode the file as a PNG image.
//		    FileOutputStream outStream;
//		    try {
//
//		        outStream = new FileOutputStream(file);
//		        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream); 
//		        /* 100 to keep full quality of the image */
//		        outStream.flush();
//		        outStream.close();
//		        success = true;
//		    } catch (FileNotFoundException e) {
//		        e.printStackTrace();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//			
//		  if (success) {
//	        Toast.makeText(getApplicationContext(), "Image saved successfully.",
//	                Toast.LENGTH_SHORT).show();
//	    } else {
//	        Toast.makeText(getApplicationContext(),
//	                "Error during image saving..", Toast.LENGTH_SHORT).show();
//	    }
//	}
    Activity activity;
    
    public void getColor(View v) {  
    	  new ColorPicker(activity, this, "", Color.BLACK, Color.WHITE).show();   
    	 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void colorChanged(String key, int color) {
		// TODO Auto-generated method stub
		DrawingActivity.this.findViewById(android.R.id.content)  
		  .setBackgroundColor(color);  

//		myDrawView.coloring=color;
int col =color;
		
	System.out.println("jjjjjjjjjjjjjj"+col);	
	}
}

//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//public class MainActivity extends Activity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//}
