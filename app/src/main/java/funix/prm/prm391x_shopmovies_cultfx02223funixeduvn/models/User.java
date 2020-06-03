package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class User {
    private String displayName;
    private String email;
    private String photoUrl;
    private String authProvider;

    public static final String GOOGLE_PROVIDER = "google";
    public static final String FACEBOOK_PROVIDER = "facebook";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GOOGLE_PROVIDER, FACEBOOK_PROVIDER})
    public  @interface AuthProvider{};


    private static User currentUser;

    public User(String displayName, String email, String photoUrl, @AuthProvider String authProvider) {
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.authProvider = authProvider;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void signOut(){
        currentUser = null;
    }

    public static void setCurrentUser(User user, Context context){
        currentUser = user;
        SharedPreferences.Editor editor = context
                .getSharedPreferences("profile",Context.MODE_PRIVATE)
                .edit();
        if(currentUser != null){
            editor.putString("displayName",currentUser.getDisplayName());
            editor.putString("email",currentUser.getEmail());
            editor.putString("photoUrl",currentUser.getPhotoUrl().toString());
            editor.putString("authProvider",currentUser.getAuthProvider().toString());
        }else {
            editor.clear();
        }
        editor.apply();
    }

    public static User getCurrentUser(Context context){
        if(currentUser == null){
            // get user in store
            SharedPreferences sharedPreferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE);
            String displayName = sharedPreferences.getString("displayName", null);

            // if user not stored
            if(displayName == null){
                return null;
            }
            String email = sharedPreferences.getString("email", null);
            String photoUrl = sharedPreferences.getString("photoUrl", null);
            String authProvider = sharedPreferences.getString("authProvider", null);
            currentUser = new User(displayName, email, photoUrl, authProvider);
        }

        return currentUser;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }


    public String getAuthProvider(){
        return authProvider;
    }
}
