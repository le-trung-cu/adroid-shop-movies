package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.User;

public class MainActivity extends AppCompatActivity{
    User mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurrentUser = User.getCurrentUser(getApplicationContext());
        if (mCurrentUser == null) {
            signIn();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentUser = User.getCurrentUser(this);
    }

    private void signIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

}