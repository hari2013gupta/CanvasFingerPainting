package com.hari.canvasfingerpainting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ChoosePicture extends Activity{
Bitmap bitmap;
@Override
                    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                        InputStream stream = null;
                        if (requestCode == 101&& resultCode == Activity.RESULT_OK)
                        {
                            try 
                            {
                                // We need to recycle unused bitmaps
                                if (bitmap != null) 
                                {
                                    bitmap.recycle();
                                }
                                stream = getContentResolver().openInputStream(data.getData());
                                bitmap = BitmapFactory.decodeStream(stream);
                                ImageView imageView=(ImageView)findViewById(R.id.drawing);
                                imageView.setImageBitmap(bitmap);
                                imageView.getLayoutParams().height = bitmap.getHeight()/8;
                            }
                            catch (FileNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                            if (stream != null)
                            {
                                try
                                {
                                    stream.close();
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
}
