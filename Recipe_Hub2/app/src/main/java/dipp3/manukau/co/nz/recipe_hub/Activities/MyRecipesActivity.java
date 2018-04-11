package dipp3.manukau.co.nz.recipe_hub.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import dipp3.manukau.co.nz.recipe_hub.Adapters.SectionsPageAdapter;
import dipp3.manukau.co.nz.recipe_hub.Helpers.BottomNavigationViewHelper;
import dipp3.manukau.co.nz.recipe_hub.Fragments.CategoryTabFragmentBreakfast;
import dipp3.manukau.co.nz.recipe_hub.Fragments.CategoryTabFragmentDinner;
import dipp3.manukau.co.nz.recipe_hub.Fragments.CategoryTabFragmentLunch;
import dipp3.manukau.co.nz.recipe_hub.Fragments.CategoryTabFragmentSnacks;
import dipp3.manukau.co.nz.recipe_hub.R;

public class MyRecipesActivity extends AppCompatActivity{

    private static final String TAG = "MyRecipesActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;



        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_myrecipes);
            Log.d(TAG, "onCreate: Starting");


//Tabs Start Here
            mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

            mViewPager = (ViewPager) findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
//Tabs End
//Navigation Starts Here
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationViewBar);
            BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.getItem(1);
            menuItem.setChecked(true);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.ic_home:
                            Intent mainActivityIntent = new Intent(MyRecipesActivity.this, MainActivity.class);
                            startActivity(mainActivityIntent);
                            break;
                        case R.id.ic_myrecipes:

                            break;
                        case R.id.ic_create:
                            Intent addRecipeIntent = new Intent(MyRecipesActivity.this, AddRecipeActivity.class);
                            startActivity(addRecipeIntent);
                            break;
                        case R.id.ic_shared:
                            Intent sharedRecipesIntent = new Intent(MyRecipesActivity.this, SharedRecipesActivity.class);
                            startActivity(sharedRecipesIntent);
                            break;
                    }

                    return false;
                }
            });
//End Navigation
        }

        //Setup Pager View
        private void setupViewPager(ViewPager viewPager) {
            SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
            adapter.addFragment(new CategoryTabFragmentBreakfast(), "Breakfast");
            adapter.addFragment(new CategoryTabFragmentLunch(), "Lunch");
            adapter.addFragment(new CategoryTabFragmentDinner(), "Dinner");
            adapter.addFragment(new CategoryTabFragmentSnacks(), "Snacks");
            viewPager.setAdapter(adapter);
        }
    }

