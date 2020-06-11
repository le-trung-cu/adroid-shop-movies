package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareMediaContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.io.FileOutputStream;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients.ImageClientHelper;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.Movie;

import static com.facebook.FacebookSdk.getFacebookDomain;
import static com.facebook.FacebookSdk.isInitialized;
import static com.facebook.FacebookSdk.sdkInitialize;

public class MovieDetailActivity extends AppCompatActivity {
    private final String MOVIE_DETAIL_CODE = "movie_detail";
    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvPrice;
    private ShareButton shareButton;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final ShareDialog shareDialog = new ShareDialog(this);
        ivImage = findViewById(R.id.iv_image);
        tvTitle = findViewById(R.id.tv_title);
        tvPrice = findViewById(R.id.tv_price);
        shareButton = findViewById(R.id.share_button);
        Intent intent = getIntent();
        mMovie = (Movie) intent.getSerializableExtra(MOVIE_DETAIL_CODE);

        ImageClientHelper imageClientHelper = ImageClientHelper.getInstance();
        imageClientHelper.fetch(mMovie.getImgUrl(), new ImageClientHelper.OnImageLoad() {
            @Override
            public void onLoad(Bitmap bitmap) {
                ivImage.setImageBitmap(bitmap);
                shareMovieToFacebook(bitmap);
            }
        });
        tvTitle.setText(mMovie.getTitle());
        tvPrice.setText(mMovie.getPrice());


    }

    private void shareMovieToFacebook(Bitmap bitmap) {
        ShareButton shareButton = findViewById(R.id.share_button);

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareButton.setShareContent(content);
    }
}