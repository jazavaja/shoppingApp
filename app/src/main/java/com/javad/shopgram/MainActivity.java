package com.javad.shopgram;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.javad.shopgram.fragments.BasketFragment;
import com.javad.shopgram.fragments.CategoryFragment;
import com.javad.shopgram.fragments.HafteBazzarFragment;
import com.javad.shopgram.fragments.HomeFragment;
import com.javad.shopgram.fragments.ProfileFragment;
import com.javad.shopgram.fragments.SearchFragment;
import com.trafi.anchorbottomsheetbehavior.AnchorBottomSheetBehavior;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    View rl_search;
    public static   View bg;
    TextView txt_appName, min_value, max_value;
    ImageView home, cat, search, shop, shoping, account;
    FragmentManager fm;
    Fragment f;
    CrystalRangeSeekbar rang_seekbar;
    RadioButton labaniat;
    boolean check;
    public static AnchorBottomSheetBehavior sheetBehavior;
    FrameLayout bottomSheet;

    private void radioButton() {

        labaniat = findViewById(R.id.labaniat);
        labaniat.setChecked(false);

        labaniat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!check) {

                    labaniat.setChecked(true);
                    check = true;
                    Log.e("labaniat", "check");

                } else {

                    labaniat.setChecked(false);
                    check = false;
                    Log.e("labaniat", "uncheck");

                }

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout_activity);
        refrence();
        radioButton();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        setSelected(home,new ImageView[]{account,search,cat,shop,shoping});
        rang_seekbar.setMaxValue(4000);
        rang_seekbar.setMinValue(0);

        rang_seekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {

                min_value.setText(minValue + " تومان ");
                max_value.setText(maxValue + " تومان ");

            }
        });

    }

    private void refrence() {

        bottomSheet = findViewById(R.id.bottom_sheet);
        bg = findViewById(R.id.bg);
        sheetBehavior = AnchorBottomSheetBehavior.from(bottomSheet);

        home = findViewById(R.id.home);
        min_value = findViewById(R.id.min_value);
        max_value = findViewById(R.id.max_value);
        rang_seekbar = findViewById(R.id.rang_seekbar);
        search = findViewById(R.id.search);
        cat = findViewById(R.id.cat);
        shop = findViewById(R.id.hafteBazar);
        shoping = findViewById(R.id.shoping);
        account = findViewById(R.id.account);


        txt_appName = findViewById(R.id.txt_appname);
        rl_search = findViewById(R.id.rl_search);

        home.setOnClickListener(this);
        search.setOnClickListener(this);
        cat.setOnClickListener(this);
        shop.setOnClickListener(this);
        shoping.setOnClickListener(this);
        account.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        rl_search.setVisibility(View.GONE);

        switch (v.getId()) {

            case R.id.home:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new HomeFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setSelected(home,new ImageView[]{shoping,cat,account,search,shop});

                break;

            case R.id.search:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new SearchFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setSelected(search,new ImageView[]{shoping,cat,account,home,shop});

                rl_search.setVisibility(View.VISIBLE);
                break;

            case R.id.hafteBazar:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new HafteBazzarFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setSelected(shop,new ImageView[]{shoping,cat,account,search,home});


                break;

            case R.id.shoping:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new BasketFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setSelected(shoping,new ImageView[]{home,cat,account,search,shop});


                break;

            case R.id.cat:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new CategoryFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setSelected(cat,new ImageView[]{shoping,home,account,search,shop});



                break;

            case R.id.account:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new ProfileFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                setSelected(account,new ImageView[]{shoping,cat,home,search,shop});


                break;

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {

        fm = getSupportFragmentManager();
        f = fm.findFragmentById(R.id.frame_container);

        if (f instanceof SearchFragment) {

            if (!(sheetBehavior.getState() == AnchorBottomSheetBehavior.STATE_COLLAPSED)) {

                bg.animate().alpha(0).setDuration(200).start();
                sheetBehavior.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);

            } else {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new HomeFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

//                home.setColorFilter(getColor(R.color.colorBottomBarSelect));
//                shoping.setColorFilter(getColor(R.color.colorBottomBarUnselect));
//                shop.setColorFilter(getColor(R.color.colorBottomBarUnselect));
//                cat.setColorFilter(getColor(R.color.colorBottomBarUnselect));
//                account.setColorFilter(getColor(R.color.colorBottomBarUnselect));
//                search.setColorFilter(getColor(R.color.colorBottomBarUnselect));

            }

        } else {
            finish();
        }
    }

    public void setSelected(ImageView selectd, ImageView[] unselectd) {
        selectd.setColorFilter(R.color.colorBlue);
        for (ImageView anUnselectd : unselectd) {
            anUnselectd.setColorFilter(R.color.colorRed);
        }
    }

}
