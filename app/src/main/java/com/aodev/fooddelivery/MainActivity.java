package com.aodev.fooddelivery;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aodev.fooddelivery.data.CategoriesAdapter;
import com.aodev.fooddelivery.data.CategoriesModel;
import com.aodev.fooddelivery.data.FeaturedAdapter;
import com.aodev.fooddelivery.data.FeaturedModel;
import com.aodev.fooddelivery.data.PopularAdapter;
import com.aodev.fooddelivery.data.PopularModel;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{

    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView featuredRecyclerView;
    private FeaturedAdapter featuredAdapter;
    private RecyclerView popularRecyclerView;
    private PopularAdapter popularAdapter;

    private static final int MENU_MY_ORDER = 0;
    private static final int MENU_MY_PROFILE = 1;
    private static final int MENU_DELIVERY_ADDRESS = 2;
    private static final int MENU_PAYMENT = 3;
    private static final int MENU_CONTACT_US = 4;
    private static final int MENU_SETTING = 5;
    private static final int MENU_HELP = 6;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;
    private ImageView toggle_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
        toggle_menu = findViewById(R.id.toggle_menu);

        slidingRootNav = new SlidingRootNavBuilder(this)
//                .withToolbarMenuToggle(toolbar)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(MENU_MY_ORDER),
                createItemFor(MENU_MY_PROFILE),
                createItemFor(MENU_DELIVERY_ADDRESS),
                createItemFor(MENU_PAYMENT),
                createItemFor(MENU_CONTACT_US),
                createItemFor(MENU_SETTING),
                createItemFor(MENU_HELP)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list_menu);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        toggle_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingRootNav.openMenu();
            }
        });

        ArrayList<CategoriesModel> categoriesItem = new ArrayList<>();
        categoriesItem.add(new CategoriesModel(R.drawable.mask_group_5,"Burger"));
        categoriesItem.add(new CategoriesModel(R.drawable.mask_group_7,"Donut"));
        categoriesItem.add(new CategoriesModel(R.drawable.mask_group_6,"Pizza"));
        categoriesItem.add(new CategoriesModel(R.drawable.mask_group_8,"Mexican"));
        categoriesItem.add(new CategoriesModel(R.drawable.mask_group_9,"Asian"));
        categoriesItem.add(new CategoriesModel(R.drawable.icecream,"Ice Cream"));

        ArrayList<FeaturedModel> featuredItem = new ArrayList<>();
        String[] tag1= {"Burger","Chicken","Fast Food"};
        String[] tag2= {"COFFEE","Chicken","Fast Food"};
        featuredItem.add(new FeaturedModel(R.drawable.mask_group_2, (float) 4.5,"McDonald’s","10-15","free",tag1));
        featuredItem.add(new FeaturedModel(R.drawable.mask_group_1, (float) 4.7,"Starbuck ","10-15","$2",tag2));

        ArrayList<PopularModel> popularModels = new ArrayList<>();
        popularModels.add(new PopularModel(R.drawable.mask_group_3,(float)5.50,"Salmon Salad"));
        popularModels.add(new PopularModel(R.drawable.mask_group_4,(float)8.25,"Salmon Salad"));
        categoriesRecyclerView = findViewById(R.id.recyc_category);
        categoriesAdapter = new CategoriesAdapter(categoriesItem);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        featuredRecyclerView = findViewById(R.id.recyc_featured);
        featuredAdapter = new FeaturedAdapter(featuredItem);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        featuredRecyclerView.setAdapter(featuredAdapter);

        popularRecyclerView = findViewById(R.id.recyc_popular);
        popularAdapter = new PopularAdapter(popularModels);
        popularRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        popularRecyclerView.setAdapter(popularAdapter);
    }

    @Override
    public void onItemSelected(int position) {
        if(position == MENU_MY_ORDER){
            Toast.makeText(this, "MENU_MY_ORDER", Toast.LENGTH_SHORT).show();
        }else if(position == MENU_MY_PROFILE){
            Toast.makeText(this, "MENU_MY_PROFILE", Toast.LENGTH_SHORT).show();
        }else if(position == MENU_DELIVERY_ADDRESS){
            Toast.makeText(this, "MENU_DELIVERY_ADDRESS", Toast.LENGTH_SHORT).show();
        }else if(position == MENU_PAYMENT){
            Toast.makeText(this, "MENU_PAYMENT", Toast.LENGTH_SHORT).show();
        }else if(position == MENU_CONTACT_US){
            Toast.makeText(this, "MENU_CONTACT_US", Toast.LENGTH_SHORT).show();
        }else if(position == MENU_SETTING){
            Toast.makeText(this, "MENU_SETTING", Toast.LENGTH_SHORT).show();
        }else if(position == MENU_HELP){
            Toast.makeText(this, "MENU_HELP", Toast.LENGTH_SHORT).show();
        }
        slidingRootNav.closeMenu();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new MenuItem(screenIcons[position], screenTitles[position])
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.textColorPrimary))
                .withSelectedTextTint(color(R.color.textColorPrimary));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}