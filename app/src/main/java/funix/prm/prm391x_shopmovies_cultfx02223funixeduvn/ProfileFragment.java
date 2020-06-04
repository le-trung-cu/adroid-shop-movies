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

import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients.ImageClient;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.clients.ImageClientHelper;
import funix.prm.prm391x_shopmovies_cultfx02223funixeduvn.models.User;

public class ProfileFragment extends Fragment {
    private User mUser;
    private TextView tvDisplayName;
    private TextView tvEmail;
    private ImageView ivImage;
    private LinearLayout llInfoUer;
    private LinearLayout llSignIn;

    private OnSigOutListener mListener;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnSigOutListener){
            mListener = (OnSigOutListener) context;
        }
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
                mListener.onSignOut();
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

    @Override
    public void onResume() {
        super.onResume();
        mUser = User.getCurrentUser(getActivity());
        if(mUser != null){

            tvDisplayName.setText(mUser.getDisplayName());
            tvEmail.setText(mUser.getEmail());
            ImageClientHelper imageClientHelper = ImageClientHelper.getInstance();
            if(!mUser.getPhotoUrl().isEmpty()){
                imageClientHelper.fetch(mUser.getPhotoUrl(), new ImageClientHelper.OnImageLoad() {
                    @Override
                    public void onLoad(Bitmap bitmap) {
                        ivImage.setImageBitmap(bitmap);
                    }
                });
            }

            llInfoUer.setVisibility(View.VISIBLE);
            llSignIn.setVisibility(View.GONE);
        }else {
            llInfoUer.setVisibility(View.GONE);
            llSignIn.setVisibility(View.VISIBLE);
        }
    }

    public interface OnSigOutListener{
        void onSignOut();
    }
}