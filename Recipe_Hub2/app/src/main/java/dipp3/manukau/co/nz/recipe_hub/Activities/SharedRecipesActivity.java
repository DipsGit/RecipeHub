package dipp3.manukau.co.nz.recipe_hub.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import dipp3.manukau.co.nz.recipe_hub.Helpers.BottomNavigationViewHelper;
import dipp3.manukau.co.nz.recipe_hub.R;

public class SharedRecipesActivity extends AppCompatActivity{

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedrecipes);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent mainActivityIntent = new Intent(SharedRecipesActivity.this, MainActivity.class);
                        startActivity(mainActivityIntent);
                        break;
                    case R.id.ic_myrecipes:
                        Intent myRecipesIntent = new Intent(SharedRecipesActivity.this, MyRecipesActivity.class);
                        startActivity(myRecipesIntent);
                        break;
                    case R.id.ic_create:
                        Intent addRecipesIntent = new Intent(SharedRecipesActivity.this, AddRecipeActivity.class);
                        startActivity(addRecipesIntent);
                        break;
                    case R.id.ic_shared:

                        break;
                }


                return false;
            }
        });
    }
}
