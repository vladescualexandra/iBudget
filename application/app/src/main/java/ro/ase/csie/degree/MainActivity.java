package ro.ase.csie.degree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import ro.ase.csie.degree.authentication.user.User;
import ro.ase.csie.degree.fragments.DayFragment;
import ro.ase.csie.degree.fragments.MonthFragment;
import ro.ase.csie.degree.fragments.TodayFragment;
import ro.ase.csie.degree.fragments.TotalFragment;
import ro.ase.csie.degree.fragments.WeekFragment;
import ro.ase.csie.degree.fragments.YearFragment;
import ro.ase.csie.degree.settings.SettingsActivity;


import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_TRANSACTION = 201;
    private String USER_KEY;

    private ImageButton btn_settings;
    private ImageButton btn_add;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();


    }

    private void initComponents() {
        btn_add = findViewById(R.id.main_add_transaction);
        btn_settings = findViewById(R.id.main_settings);

        btn_add.setOnClickListener(addEventListener());
        btn_settings.setOnClickListener(settingsEventListener());

        tabLayout = findViewById(R.id.main_tabs);
        tabLayout.addOnTabSelectedListener(changeTabEventListener());

        USER_KEY = new User().getUID(getApplicationContext());
    }

    private View.OnClickListener addEventListener() {
        return v -> {
            Intent intent = new Intent(getApplicationContext(), AddTransactionActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TRANSACTION);
        };
    }

    private View.OnClickListener settingsEventListener() {
        return v -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        };
    }

    private TabLayout.OnTabSelectedListener changeTabEventListener() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()) {
                    case 2:
                        fragment = new MonthFragment();
                        break;
                    case 3:
                        fragment = new YearFragment();
                        break;
                    case 4:
                        fragment = new TotalFragment();
                        break;
                    default:
                        fragment = new DayFragment();
                        break;
                }
                show(fragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    private void show(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment_list, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}