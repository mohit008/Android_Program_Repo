package com.mohit.program.download;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author @ Mohit Soni on 27-04-2018 12:30.
 */

public class ImageDownloadSample extends Activity {
    // stream to read
    InputStream in;
    public ImageDownloadSample() {
        // initialise
        new DownloadImageTask().execute("http://www.imgion.com/images/01/Pink-Lotus-Flower.jpg");
    }

    /**
     * creating class for download task process
     */
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urls = url[0];
            Bitmap bitmap = null;
            try {
                // opening input stream to url
                in = new java.net.URL(urls).openStream();
                // decoding bitmap
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bit) {
        }
    }
    public class DownloadImageTask2 extends AsyncTask<String, Void, Bitmap> {

        InputStream in;
        ImageView imageView;
        String urls;
        Bitmap bitmap;
        int responseCode = -1;
        public DownloadImageTask2( String urls,ImageView imageView){
            this.imageView = imageView;
            this.urls = urls;
        }
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... string) {
            URL url = null;
            try {
                url = new URL("http://udwebs.xyz/uploads/profile_image/profile_image_19.jpg");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    in = httpURLConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bit) {
            imageView.setImageBitmap(bit);
        }
    }
}
