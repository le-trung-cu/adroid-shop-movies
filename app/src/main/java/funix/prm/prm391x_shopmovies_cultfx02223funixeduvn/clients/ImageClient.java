package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;

public class ImageClient extends AsyncTask<String, Void, Bitmap> {
    private static Hashtable<String, Bitmap> imagesCached = new Hashtable<>();
    private static ImageClient instance;
    private OnImageLoad mListener;

    public void setOnLoadListener(OnImageLoad listener){
        mListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        if(imagesCached.contains(strings[0])){
            return imagesCached.get(strings[0]);
        }
        try {
            URL imgUrl = new URL(strings[0]);
            HttpsURLConnection connection = (HttpsURLConnection) imgUrl.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            imagesCached.put(strings[0], bitmap);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mListener.onLoaded(bitmap);
    }

    public interface OnImageLoad{
        void onLoaded(Bitmap bitmap);
    }
}
