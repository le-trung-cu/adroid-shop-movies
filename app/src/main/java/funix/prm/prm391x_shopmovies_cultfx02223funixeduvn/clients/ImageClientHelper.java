package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients;

import android.graphics.Bitmap;

import java.util.Hashtable;

public class ImageClientHelper {

    private Hashtable<String, Bitmap> imagesCached;

    private static ImageClientHelper instance;

    private ImageClientHelper(){
        imagesCached = new Hashtable<>();
    }

    public static ImageClientHelper getInstance(){
        if(instance == null){
            instance = new ImageClientHelper();
        }
        return instance;
    }

    public void fetch(final String url, final ImageClientHelper.OnImageLoad onImageLoad){
        if(imagesCached.containsKey(url)){
            onImageLoad.onLoad(imagesCached.get(url));
            return;
        }
        ImageClient imageClient = new ImageClient();
        imageClient.setOnLoadListener(new ImageClient.OnImageLoad() {
            @Override
            public void onLoaded(Bitmap bitmap) {
                imagesCached.put(url, bitmap);
                onImageLoad.onLoad(bitmap);
            }
        });
        imageClient.execute(url);
    }


    public interface OnImageLoad{
        public void onLoad(Bitmap url);
    }
}
