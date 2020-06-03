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
                        fragment = new MoviesFragment();
                        break;
                    case R.id.item_menu_profile:
                        fragment = new ProfileFragment();
                        break;
                }
                showFragment(fragment);
                return true;
            }
        });
        navigationView.setSelectedItemId(R.id.item_menu_movies);
    }

    private void showFragment(Fragment fragmentActive) {
        FragmentTransaction transition = fragmentManager.beginTransaction();

        Class fragmentActiveClass = fragmentActive.getClass();

        Class[] fragmentClasses = {MoviesFragment.class, ProfileFragment.class};

        for (Class fragmentClass : fragmentClasses) {
            String fragmentTag = fragmentClass.getName();
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
            // hide other fragments
            if (fragment != null && fragmentClass != fragmentActiveClass) {
                transition.hide(fragment);
            }
        }
        // show fragment
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentActiveClass.getName());
        if (fragment == null) {
            transition.add(R.id.fl_contain, fragmentActive, fragmentActiveClass.getName());
        } else {
            transition.show(fragment);
        }
        transition.commit();
    }
}