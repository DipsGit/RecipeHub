package dipp3.manukau.co.nz.recipe_hub.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import dipp3.manukau.co.nz.recipe_hub.Fragments.HelpDialog;
import dipp3.manukau.co.nz.recipe_hub.Helpers.BottomNavigationViewHelper;
import dipp3.manukau.co.nz.recipe_hub.Database.MyDatabase;
import dipp3.manukau.co.nz.recipe_hub.R;

public class MainActivity extends AppCompatActivity {

    Button aR, mR, rH;
    public static MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"RecipeDB").allowMainThreadQueries().build();
//        myDatabase.dao().nukeTable();

        aR = findViewById(R.id.addRecipesBtn);
        aR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddRecipeActivity.class);
                startActivity(i);
            }
        });
        mR = findViewById(R.id.myRecipesBtn);
        mR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MyRecipesActivity.class);
                startActivity(i);
            }
        });
        rH = findViewById(R.id.recipeHubBtn);
        rH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SharedRecipesActivity.class);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:

                        break;
                    case R.id.ic_myrecipes:
                        Intent myRecipesIntent = new Intent(MainActivity.this, MyRecipesActivity.class);
                        startActivity(myRecipesIntent);
                        break;
                    case R.id.ic_create:
                        Intent addRecipeIntent = new Intent(MainActivity.this, AddRecipeActivity.class);
                        startActivity(addRecipeIntent);
                        break;
                    case R.id.ic_shared:
                        Intent sharedRecipesIntent = new Intent(MainActivity.this, SharedRecipesActivity.class);
                        startActivity(sharedRecipesIntent);
                        break;
                }


                return false;
            }
        });

    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int idd = item.getItemId();
        if(idd==R.id.help){
//            Toast.makeText(this, "Help",Toast.LENGTH_SHORT).show();
        openDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    public void openDialog(){
        HelpDialog helpDialog = new HelpDialog();
        helpDialog.show(getSupportFragmentManager(), "help dialog");
    }
}
