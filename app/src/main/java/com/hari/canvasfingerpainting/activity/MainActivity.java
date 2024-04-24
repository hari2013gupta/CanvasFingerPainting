package com.hari.canvasfingerpainting.activity;

import java.io.File;
import java.util.UUID;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.hari.canvasfingerpainting.ColorPicker;
import com.hari.canvasfingerpainting.DrawingView;
import com.hari.canvasfingerpainting.R;
import com.hari.canvasfingerpainting.activity.ColorPickerrActivity;

import androidx.core.content.ContextCompat;

public class MainActivity extends BaseActivity implements ColorPicker.OnColorChangedListener, OnClickListener {
    //custom drawing view
    private DrawingView drawView;
    private String colorr = "#FFFFFF", color = "#000000";
    private Button btn_pick_pallete, btn3;
    View colors_palette, pallete_backg;
    int count = 0;
    boolean counter = false;
    private ImageButton currPaint, btn2_palette, newBtn, saveBtn, opacityBtn, drawBtn, eraseBtn, galleryBtn, cameraBtn;
    int colorrr;
    protected static final int CAMERA_PIC_REQUEST = 0;
    Bitmap bitmap;
    private static int RESULT_LOAD_IMAGE = 1;
    private float smallpenBrush, smallestBrush, smallerBrush, smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

        this.activity = this;

        //get drawing view
        drawView = (DrawingView) findViewById(R.id.drawing);

        colorrr = Color.parseColor(colorr);
        drawView.setColor(color);
        drawView.setBackgroundColor(colorrr);

        //hari_get the palette and first color button
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors2);
        currPaint = (ImageButton) paintLayout.getChildAt(3);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        smallpenBrush = getResources().getInteger(R.integer.vvvsmall_size);

        smallestBrush = getResources().getInteger(R.integer.vvsmall_size);
        smallerBrush = getResources().getInteger(R.integer.vsmall_size);

        smallBrush = getResources().getInteger(R.integer.small_size);

        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
////////hari_ex////////////////////////////
        colors_palette = (View) findViewById(R.id.palette_colors);
        pallete_backg = (View) findViewById(R.id.palette_backg);

        btn_pick_pallete = (Button) findViewById(R.id.button1_bg);

        btn2_palette = (ImageButton) findViewById(R.id.button2_palette);
//			btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.button3);

        btn_pick_pallete.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//			    	 Intent intent=new Intent(getApplicationContext(), ColorPickerrActivity.class);
////			    		i.putExtra("hari_ans", correct);
//			    	 startActivity(intent);

//					Button imgView = (Button)v;
//					String color = v.getTag().toString();
//					color colorrr;
                colorrr = Color.parseColor(colorr);//by hari
//					drawView.setColor(color);
                drawView.setBackgroundColor(colorrr);

            }
        });


//		  btn2_palette.setOnClickListener(new OnClickListener() {
//			  
//				@Override
//				public void onClick(View v) {
////			    	 Intent intent=new Intent(getApplicationContext(), DrawingActivity.class);
//////			    		i.putExtra("hari_ans", correct);
////			    	 startActivity(intent);
//
//if(count%2!=0){
////	colors_palette.setVisibility(View.VISIBLE);
////	pallete_backg.setVisibility(View.GONE);
////	btn2_palette.setText("Brush Color");
//}	
//else if(count%2==0){
////	colors_palette.setVisibility(View.GONE);
////	pallete_backg.setVisibility(View.VISIBLE);
////	btn2_palette.setText("Background Color");
//}				count++;
//				drawView = (DrawingView)findViewById(R.id.drawing);
////				LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
////				currPaint = (ImageButton)paintLayout.getChildAt(0);
////				currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
//				}
//			});

        btn3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ColorPickerrActivity.class);
//			    		i.putExtra("hari_ans", correct);
                startActivity(intent);

            }
        });//////////////////////////////////////////////////hariendth_functjion///////////////
        drawBtn = (ImageButton) findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);
        //set initial size
        drawView.setBrushSize(smallBrush);
        eraseBtn = (ImageButton) findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        newBtn = (ImageButton) findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        saveBtn = (ImageButton) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

        opacityBtn = (ImageButton) findViewById(R.id.opacity_btn);
        opacityBtn.setOnClickListener(this);

        galleryBtn = (ImageButton) findViewById(R.id.gallery_btn);
        galleryBtn.setOnClickListener(this);

        cameraBtn = (ImageButton) findViewById(R.id.camera_btn);
        cameraBtn.setOnClickListener(this);

    }//endonecrea

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //	user clicked paint
    public void paintClicked(View view) {

        //set erase false
        drawView.setErase(false);
        drawView.setPaintAlpha(100);
        drawView.setBrushSize(drawView.getLastBrushSize());

        if (view != currPaint) {
            ImageButton imgView = (ImageButton) view;
            color = view.getTag().toString();
            drawView.setColor(color);

            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint = (ImageButton) view;
        }
    }

    //	//user clicked background
    public void backgClicked(View view) {
        //use chosen color

//		if(view!=currPaint){
        ImageButton imgView = (ImageButton) view;
        colorr = view.getTag().toString();
        System.out.println("color backgClicked" + colorr);
//			 drawView .setBackgroundColor(color);  
//			color colorrr;
        colorrr = Color.parseColor(colorr);
//			drawView.setColor(color);
        drawView.setBackgroundColor(colorrr);
        //update ui
        imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
        currPaint = (ImageButton) view;
//		}
    }

    @Override
    public void onClick(View view) {

//		if(view.getId()==R.id.draw_btn){
//			//draw button clicked
//			final Dialog brushDialog = new Dialog(this);
//			brushDialog.setTitle("Select brush size:");
//			brushDialog.setContentView(R.layout.brush_chooser);
//			//listen for clicks on size buttons
//			ImageButton smallPenBtn = (ImageButton)brushDialog.findViewById(R.id.vvvsmall_brush);
//			smallPenBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(smallpenBrush);
//					drawView.setLastBrushSize(smallpenBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton smallestBtn = (ImageButton)brushDialog.findViewById(R.id.vvsmall_brush);
//			smallestBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(smallestBrush);
//					drawView.setLastBrushSize(smallestBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton smallerBtn = (ImageButton)brushDialog.findViewById(R.id.vsmall_brush);
//			smallerBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(smallerBrush);
//					drawView.setLastBrushSize(smallerBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
//			smallBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(smallBrush);
//					drawView.setLastBrushSize(smallBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
//			mediumBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(mediumBrush);
//					drawView.setLastBrushSize(mediumBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
//			largeBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(largeBrush);
//					drawView.setLastBrushSize(largeBrush);
//					brushDialog.dismiss();
//				}
//			});
//			//show and wait for user interaction
//			brushDialog.show();
//		}
        if (view.getId() == R.id.draw_btn) {
/////////////////////////////////////////
            final Dialog seekDialog = new Dialog(this);
            seekDialog.setTitle("Set brush size:");
            seekDialog.setContentView(R.layout.brush_choose);
            final TextView seekTxt = (TextView) seekDialog.findViewById(R.id.brsh_txt);
            final SeekBar seekBrsh = (SeekBar) seekDialog.findViewById(R.id.brsh_seek);

            seekBrsh.setMax(50);
            int currLevel = getResources().getInteger(R.integer.small_size);
            seekTxt.setText(currLevel + "%");
            seekBrsh.setProgress(currLevel);
            seekBrsh.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int prog = progress * 2;
                    seekTxt.setText(Integer.toString(prog) + "%");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

            });
            Button brushSizeBtn = (Button) seekDialog.findViewById(R.id.brsh_ok);
            brushSizeBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    drawView.setErase(false);
                    drawView.setBrushSize(seekBrsh.getProgress());
                    drawView.setLastBrushSize(seekBrsh.getProgress());

//					drawView.setPaintAlpha(seekBrsh.getProgress());
                    drawView.setColor(color);//harijugd_temp
                    seekDialog.dismiss();
                }
            });
            seekDialog.show();
            //////////////////////hariend fro seek

//			final Dialog brushDialog = new Dialog(this);
//			brushDialog.setTitle("Set brush size:");
//			brushDialog.setContentView(R.layout.brush_choose);
//
//			ImageButton smallPenBtn = (ImageButton)brushDialog.findViewById(R.id.vvvsmall_brush);
//			smallPenBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(smallpenBrush);
//					drawView.setLastBrushSize(smallpenBrush);
//					brushDialog.dismiss();
//				}
//
//			});
//			brushDialog.show();
        } else if (view.getId() == R.id.new_btn) {
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    drawView.startNew();
//					drawView.setBackground(null);
//					colorr = view.getTag().toString();
                    colorr = "#FFFFFF";
//					System.out.println("color backgClicked"+colorr);
//					 drawView .setBackgroundColor(color);  
//					color colorrr;
                    colorrr = Color.parseColor(colorr);
//					drawView.setColor(color);
                    drawView.setBackgroundColor(colorrr);

//					drawView.setBackgroundColor(color.white);
//			        colorr="#ffffffff";//doing by anurg.for erasing the color with backg_color....(hari)...
//					colorrr= Color.parseColor(colorr);

                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();
        } else if (view.getId() == R.id.save_btn) {
            if (!getWritePermission()) {
                showToast("Please allow storage permission to save in gallery!");
                return;
            }

            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Save drawing");
            saveDialog.setMessage("Save drawing to device Gallery?");
            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    drawView.setDrawingCacheEnabled(true);
                    //attempt to save
                    String imgSaved = Images.Media.insertImage(
                            getContentResolver(), drawView.getDrawingCache(),
                            UUID.randomUUID().toString() + ".png", "drawing");

                    if (imgSaved != null) {
                        Toast savedToast = Toast.makeText(getApplicationContext(),
                                "Drawing image saved to Gallery!", Toast.LENGTH_SHORT);
                        savedToast.show();
                    } else {
                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                "Error in saving image to Gallery.", Toast.LENGTH_SHORT);
                        unsavedToast.show();
                    }
                    drawView.destroyDrawingCache();
                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            saveDialog.show();
        } else if (view.getId() == R.id.opacity_btn) {
            final Dialog seekDialog = new Dialog(this);
            seekDialog.setTitle("Opacity level:");
            seekDialog.setContentView(R.layout.opac_chooser);
            final TextView seekTxt = (TextView) seekDialog.findViewById(R.id.opq_txt);
            final SeekBar seekOpq = (SeekBar) seekDialog.findViewById(R.id.opacity_seek);

            seekOpq.setMax(100);
            int currLevel = drawView.getPaintAlpha();
            seekTxt.setText(currLevel + "%");
            seekOpq.setProgress(currLevel);
            seekOpq.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seekTxt.setText(Integer.toString(progress) + "%");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

            });
            Button opqBtn = (Button) seekDialog.findViewById(R.id.opq_ok);
            opqBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setPaintAlpha(seekOpq.getProgress());
                    seekDialog.dismiss();
                }
            });
            seekDialog.show();
        } else if (view.getId() == R.id.erase_btn) {
            //switch to erase - choose size
//			final Dialog brushDialog = new Dialog(this);
//			brushDialog.setTitle("Eraser size:");
//			brushDialog.setContentView(R.layout.brush_chooser);
            //size buttons
//			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
//			smallBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(true);
//					drawView.setBrushSize(smallBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
//			mediumBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {

//			drawView.clearPoints();
            drawView.setColor(colorr);//doing by anu.for erasing the color with backg_color....(hari)...
            drawView.setErase(true);
            drawView.setBrushSize(mediumBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
//			largeBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(true);
//					drawView.setBrushSize(largeBrush);
//					brushDialog.dismiss();
//				}
//			});
//			brushDialog.show();
        }
//		else if(view.getId()==R.id.button2){
        //new button
//			final Dialog colorDialog = new Dialog(this);
//			colorDialog.setTitle("Select a color:");
//			colorDialog.setContentView(R.layout.color_chooser);
        //listen for clicks on size buttons
//			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
//			smallBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(smallBrush);
//					drawView.setLastBrushSize(smallBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
//			mediumBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(mediumBrush);
//					drawView.setLastBrushSize(mediumBrush);
//					brushDialog.dismiss();
//				}
//			});
//			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
//			largeBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					drawView.setErase(false);
//					drawView.setBrushSize(largeBrush);
//					drawView.setLastBrushSize(largeBrush);
//					brushDialog.dismiss();
//				}
//			});
        //show and wait for user interaction
//			colorDialog.show();
//		}

        else if (view.getId() == R.id.gallery_btn) {
            if (!getWritePermission()) {
                showToast("Please allow storage permission to save in gallery!");

                return;
            }

//	    		    	setDrawingThemefrmGallery();
///////////////////////////////////////////////////////////////////////
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("Open gallery");
            newDialog.setMessage("Set background image (you will not lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    setDrawingThemefrmGallery();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();

            //////////////////////////////////////////////////////////////////////////
        } else if (view.getId() == R.id.camera_btn) {

            System.out.println("-----------sharing---------");

            ////////////////////////sharing
////////////////////////////////////////////hari_experiment///////////////////////
//	        FileInputStream ifs;
//            try {
//
//				FileOutputStream fs = openFileOutput("message_image", Context.MODE_PRIVATE);
//                    bitmap.compress(CompressFormat.PNG, 100, fs);
//                    ifs = openFileInput("message_image");
//            } catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                    return;
//            }
//            // inserts file pointed to by ifs into image gallery
//            String url = Images.Media.insertImage(getContentResolver(),
//                    BitmapFactory.decodeStream(ifs),
//                    "Message image1", "Message image");

            // alternative: inserts mBitmap into image gallery
/*              String url = Images.Media.insertImage(getContentResolver(),
                mBitmap, "Message image1", "Message image");
*/
            // creates the Intent to open the messaging app
            // with the image at url attached
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra("sms_body", "Message created using FingerText");
//sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(url));
            sendIntent.putExtra(Intent.EXTRA_STREAM, "fffffffffffff");
            sendIntent.setType("image/png");
            startActivity(sendIntent);
            /* TODO delete the image from the content provider
             * following line deletes the image, but too soon!
             */
//getContentResolver().delete(Uri.parse(url), null, null);

            //////////////////////////////old sharing_cocde///////////
//			String mailBody = drawView.toString();
//	    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//					sharingIntent.setType("image");
//					sharingIntent.setAction(Intent.ACTION_GET_CONTENT);
////			        startActivityForResult(Intent.createChooser(sharingIntent, "Select Picture"),1);
//					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mailBody);       
//					sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check this");
//					startActivity(Intent.createChooser(sharingIntent,"Share using"));
////////////////////////////////////////////////////////					

            //new button
//	        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
//	        newDialog.setTitle("New drawing");
//	        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
//	        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//	            public void onClick(DialogInterface dialog, int which){
////	                Intent intent = new Intent(getApplicationContext(), ChoosePicture.class); 
////	                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
////	                startActivity(intent);
//
//	            }
//
//	        });
//	        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
//	            public void onClick(DialogInterface dialog, int which){
//	                dialog.cancel();
//	            }
//	        });
//	        newDialog.show();
        }

    }

    public Boolean getWritePermission() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU || HasPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            return true;
        else
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        return false;
    }

    public static Boolean HasPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(context,
                    permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        } else
            return true;
        return false;
    }

    //////////statring other functions////////////////////////////////////////
    public void setDrawingThemefrmGallery() {
        // To open up a gallery browser
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        // To handle when an image is selected from the browser, add the following to your Activity
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri currImageURI = data.getData();
                String s = getRealPathFromURI(currImageURI);
                File file = new File(s);

                if (file.exists()) {
                    Drawable d = Drawable.createFromPath(file.getAbsolutePath());
//    drawView.setBackground(d);
                } else {
                    // file does not exist
                }

            }
            //////////////////cam
//    InputStream stream = null;
//    if (requestCode == 101&& resultCode == Activity.RESULT_OK)
//    {
//        try 
//        {
//            // We need to recycle unused bitmaps
//            if (bitmap != null) 
//            {
//                bitmap.recycle();
//            }
//            stream = getContentResolver().openInputStream(data.getData());
//            bitmap = BitmapFactory.decodeStream(stream);
//            ImageView imageView=(ImageView)findViewById(R.id.drawing);
//            imageView.setImageBitmap(bitmap);
//            imageView.getLayoutParams().height = bitmap.getHeight()/8;
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        if (stream != null)
//        {
//            try
//            {
//                stream.close();
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }

            //////////////////endcam
        }
    }

    /**
     * @param contentURI
     * @return
     */
    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////enduserfunction//////////////////////
    @Override
    public void colorChanged(String str, int color) {
//		  MainActivity.this.findViewById(android.R.id.content)  
//		  .setBackgroundColor(color);  

//		 MainActivity.this.findViewById(android.R.id.drawView)  
        drawView.setBackgroundColor(color);

    }

    Activity activity;

//	 public void getColor(View v) {  
//	  new ColorPicker(activity, this, "", Color.BLACK, Color.WHITE).show();   
//	 }

    int backButtonCount = 0;

    @Override
    public void onBackPressed() {
//			showAlert("Dear Users, If you like our app please give us Rating of 5 stars.");
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onBackPressed();
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        } else {
            backButtonCount++;
            Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();
        }
    }

//    public void showAlert(String msg) {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setTitle("Canvas Finger Painting");
//        builder1.setMessage(msg);
//        builder1.setCancelable(true);
//        builder1.setPositiveButton("Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        String getURL = "https://play.google.com/store/apps/details?id=com.hari.canvasfingerpainting";
////	        		Intent intent=new Intent(getApplicationContext(), getURL);
////	    			startActivity(intent);
//
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getURL));
//                        startActivity(browserIntent);
////	        		System.out.println("----------------exti---------------");//UC wb
////	        		Context context=view.getContext();
////	        		Intent thingyToInstall=new Intent(Intent.ACTION_VIEW);
////	        		thingyToInstall.setDataAndType(Uri.parse(getURL), null);
////	        		context.startActivity(thingyToInstall);
//                    }
//                });
//        builder1.setNegativeButton("No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
////	                dialog.cancel();
//                        dialog.cancel();
//                        System.runFinalizersOnExit(true);
//                        System.exit(0);
//
//                    }
//                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }

/////////////////////////////end_exit=onBackpressed

    /////////////////////////////////////////////////////	 colors_palette
    public void BgUpDown(final View view) {
        if (!isPanelShown()) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);

            pallete_backg.startAnimation(bottomUp);
            pallete_backg.setVisibility(View.VISIBLE);
            colors_palette.setVisibility(View.GONE);
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            pallete_backg.startAnimation(bottomDown);
            pallete_backg.setVisibility(View.GONE);
            colors_palette.setVisibility(View.GONE);
        }
    }

    private boolean isPanelShown() {
        return pallete_backg.getVisibility() == View.VISIBLE;
    }

    ///////////////////////////////////////////////////////////////
    public void slideUpDown(final View view) {
        if (!watPanelShown()) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);

            colors_palette.startAnimation(bottomUp);
            colors_palette.setVisibility(View.VISIBLE);
            pallete_backg.setVisibility(View.GONE);
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            colors_palette.startAnimation(bottomDown);
            colors_palette.setVisibility(View.GONE);
            pallete_backg.setVisibility(View.GONE);
        }
    }

    private boolean watPanelShown() {
        return colors_palette.getVisibility() == View.VISIBLE;
    }

    //send trackball event to drawing view
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        drawView.requestFocus();
        return drawView.onTrackballEvent(ev);
    }

}
