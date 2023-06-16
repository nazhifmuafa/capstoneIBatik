package com.capstoneprodukbangkit.batik.ui.drawer;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstoneprodukbangkit.batik.R;
import com.capstoneprodukbangkit.batik.adapter.drawer.DrawerAdapter;
import com.capstoneprodukbangkit.batik.adapter.drawer.DrawerItem;
import com.capstoneprodukbangkit.batik.adapter.drawer.SimpleItem;
import com.capstoneprodukbangkit.batik.adapter.drawer.SpaceItem;
import com.capstoneprodukbangkit.batik.ui.dashboard.DashboardFragment;
import com.capstoneprodukbangkit.batik.ui.find.FindFragment;
import com.capstoneprodukbangkit.batik.ui.profile.ProfileFragment;
import com.capstoneprodukbangkit.batik.ui.settings.SettingsFragment;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class SampleActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_FAVORITE = 2;
    private static final int POS_FIND = 3;
    private static final int POS_SHOP = 4;
    private static final int POS_SETTINGS = 5;
    private static final int POS_PROFILE = 6;
    private static final int POS_ABOUT_US = 7;
    private static final int POS_LOGOUT = 9;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav =new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_FAVORITE),
                createItemFor(POS_FIND),
                createItemFor(POS_SHOP),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_PROFILE),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(70),
                createItemFor(POS_LOGOUT)
        ));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);

    }

    @Override
    public void OnItemSelected(int position) {

        if (position == POS_LOGOUT){
            finish();
        }

        else if (position == POS_DASHBOARD){
            Fragment dashboardFragment = new DashboardFragment();
            showFragment(dashboardFragment);
        }
        //nt
        else if (position == POS_FAVORITE){
            Fragment settingsFragment = new SettingsFragment();
            showFragment(settingsFragment);
        }

        else if (position == POS_FIND){
            Fragment findFragment = new FindFragment();
            showFragment(findFragment);
        }
        //nt
        else if (position == POS_SHOP){
            Fragment settingsFragment = new SettingsFragment();
            showFragment(settingsFragment);
        }

        else if (position == POS_SETTINGS){
            Fragment settingsFragment = new SettingsFragment();
            showFragment(settingsFragment);
        }

        else if (position == POS_PROFILE){
            Fragment profileFragment = new ProfileFragment();
            showFragment(profileFragment);
        }
        //nt
        else if (position == POS_ABOUT_US){
            Fragment settingsFragment = new SettingsFragment();
            showFragment(settingsFragment);
        }

        slidingRootNav.closeMenu();
    }
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.btn))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.btn))
                .withSelectedTextTint(color(R.color.btn));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_ActivityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0){
                icons[i] = ContextCompat.getDrawable(this,id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }
}