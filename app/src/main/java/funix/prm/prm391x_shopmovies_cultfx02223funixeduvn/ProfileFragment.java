package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients.ImageClientHelper;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.User;

public class ProfileFragment extends Fragment {
    private TextView tvDisplayName;
    private TextView tvEmail;
    private ImageView ivImage;
    private LinearLayout llInfoUer;
    private LinearLayout llSignIn;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvDisplayName = view.findViewById(R.id.tv_display_name);
        tvEmail = view.findViewById(R.id.tv_email);
        ivImage = view.findViewById(R.id.iv_image);
        llInfoUer = view.findViewById(R.id.ll_info_user_sign_in);
        llSignIn = view.findViewById(R.id.ll_sign_in);

        Button btnSignOut = view.findViewById(R.id.btn_sign_out);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User mCurrentUser = User.getCurrentUser(getActivity().getApplicationContext());
                if (mCurrentUser.getAuthProvider().equals(User.GOOGLE_PROVIDER)) {
                    User.SignOutGoogle(getActivity())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    User.setCurrentUser(null,getActivity().getApplicationContext());
                                    signIn();
                                }
                            });
                }else if(mCurrentUser.getAuthProvider().equals(User.FACEBOOK_PROVIDER)) {
                    LoginManager.getInstance().logOut();
                    User.setCurrentUser(null,getActivity().getApplicationContext());
                    signIn();
                }
            }
        });

        Button btnSignIn = view.findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(){
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        User mCurrentUser = User.getCurrentUser(getActivity());
        if(mCurrentUser != null){

            tvDisplayName.setText(mCurrentUser.getDisplayName());
            tvEmail.setText(mCurrentUser.getEmail());
            ImageClientHelper imageClientHelper = ImageClientHelper.getInstance();
            if(!mCurrentUser.getPhotoUrl().isEmpty()){
                try {
                    imageClientHelper.fetch(mCurrentUser.getPhotoUrl(), new ImageClientHelper.OnImageLoad() {
                        @Override
                        public void onLoad(Bitmap bitmap) {
                            ivImage.setImageBitmap(bitmap);
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getActivity(), "user picture erro", Toast.LENGTH_SHORT).show();
                }
            }

            llInfoUer.setVisibility(View.VISIBLE);
            llSignIn.setVisibility(View.GONE);
        }else {
            llInfoUer.setVisibility(View.GONE);
            llSignIn.setVisibility(View.VISIBLE);
        }
    }
}