package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.User;

public class MainActivity extends AppCompatActivity
        implements ProfileFragment.OnSigOutListener{
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

    private void signOut() {
        if (mCurrentUser.getAuthProvider().equals(User.GOOGLE_PROVIDER)) {
            User.SignOutGoogle(this)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    User.setCurrentUser(null, getApplicationContext());
                    mCurrentUser = null;
                    signIn();
                }
            });
        }else if(mCurrentUser.getAuthProvider().equals(User.FACEBOOK_PROVIDER)) {
            LoginManager.getInstance().logOut();
            mCurrentUser = null;
            signIn();
        }
    }

    private void signIn(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSignOut() {
        signOut();
    }
}