package funix.prm.prm391x_shopmovies_cultfx02223funixeduvn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {
    private FragmentManager fragmentManager;
    private Fragment mFragmentActive;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fragmentManager = getFragmentManager();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navigationView = view.findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.item_menu_movies:
                        if(!exitsFragment(MoviesFragment.class.getName())){
                            fragment = new MoviesFragment();
                        }else {
                            fragment = fragmentManager.findFragmentByTag(MoviesFragment.class.getName());
                        }
                        break;
                    case R.id.item_menu_profile:
                        if(!exitsFragment(ProfileFragment.class.getName())){
                            fragment = new ProfileFragment();
                        }else {
                            fragment = fragmentManager.findFragmentByTag(ProfileFragment.class.getName());
                        }
                        break;
                }
                showFragment(fragment);
                return true;
            }
        });
        navigationView.setSelectedItemId(R.id.item_menu_movies);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showFragment(Fragment fragmentActive) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(mFragmentActive != null){
            transaction.hide(mFragmentActive);
        }
        if(exitsFragment(fragmentActive.getClass().getName())){
            transaction.show(fragmentActive);
        }else {
            transaction.add(R.id.fl_contain, fragmentActive, fragmentActive.getClass().getName());
        }

        transaction.commit();

        mFragmentActive = fragmentActive;
    }

    private boolean exitsFragment(String fragmentTag){
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
        return fragment != null;
    }
}