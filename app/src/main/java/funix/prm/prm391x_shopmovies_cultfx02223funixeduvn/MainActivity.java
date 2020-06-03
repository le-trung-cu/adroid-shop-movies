package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SignInActivity.class);

        User user = User.getCurrentUser(getApplicationContext());
        if(user == null){
            startActivity(intent);
        }
    }
}