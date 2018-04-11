package dipp3.manukau.co.nz.recipe_hub.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dipp3.manukau.co.nz.recipe_hub.Adapters.RecipeAdapter;
import dipp3.manukau.co.nz.recipe_hub.Database.MyDatabase;
import dipp3.manukau.co.nz.recipe_hub.Models.Recipe;
import dipp3.manukau.co.nz.recipe_hub.R;

import static dipp3.manukau.co.nz.recipe_hub.R.drawable.logo;

public class RecipeItemActivity extends AppCompatActivity {
    private static final String TAG = "RecipeItemActivity";
    TextView recipeTitle, category, servingSize, prepTime, cookTime, ingredients, instructions, id;
    ImageView imageView;
//    Recipe recipe;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_item_activity);
        Log.d(TAG, "onCreate: started");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getIncomingIntent();

    }



    private void getIncomingIntent(){
        Log.d(TAG, "Checking for incoming Intent");
        //checking for extras
        if(getIntent().hasExtra("img_url") &&
                getIntent().hasExtra("recipe_name") &&
                getIntent().hasExtra("category") &&
                getIntent().hasExtra("serving_size") &&
                getIntent().hasExtra("prep_time") &&
                getIntent().hasExtra("cook_time") &&
                getIntent().hasExtra("ingredients") &&
                getIntent().hasExtra("instructions") &&
                getIntent().hasExtra("id")) {


            String img = getIntent().getStringExtra("img_url");
            String rt = getIntent().getStringExtra("recipe_name");
            String cat = getIntent().getStringExtra("category");
            String ss = getIntent().getStringExtra("serving_size");
            String pt = getIntent().getStringExtra("prep_time");
            String ct = getIntent().getStringExtra("cook_time");
            String ing = getIntent().getStringExtra("ingredients");
            String ins = getIntent().getStringExtra("instructions");
//            int id = getIntent().getIntExtra("id", recipe.getId());

//            recipe.setId(id);
//            recipe.setRecipeName(rt);
//            recipe.setCategory(cat);
//            recipe.setRecipeName(ss);
//            recipe.setRecipeName(pt);
//            recipe.setRecipeName(ct);
//            recipe.setRecipeName(ing);
//            recipe.setRecipeName(ins);

            setRecipeFields(img, rt, cat, ss, pt, ct, ing, ins);
        }
    }

    private  void setRecipeFields(String img, String rt, String cat, String ss, String pt,
                                  String ct, String ing, String ins){
        Log.d(TAG, "Setting Recipe Fields");
        imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .asBitmap()
                .load(img)
                .into(imageView);
        recipeTitle =  findViewById(R.id.titleRecipe);
        category = findViewById(R.id.category);
        servingSize = findViewById(R.id.servingSize);
        prepTime = findViewById(R.id.prepTime);
        cookTime = findViewById(R.id.cookingTime);
        ingredients = findViewById(R.id.ingredients);
        instructions = findViewById(R.id.instructions);

        recipeTitle.setText(rt);
        category.setText(cat);
        servingSize.setText("Serving Size: " + ss);
        prepTime.setText("Preparation Time: " + pt);
        cookTime.setText("Cooking Time: " + ct);
        ingredients.setText(ing);
        instructions.setText(ins);

    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int idd = item.getItemId();
        if(idd==R.id.updateRecipe){
            Toast.makeText(this, "Update",Toast.LENGTH_SHORT).show();


        }
        if(idd==R.id.deleteRecipe){
            Toast.makeText(this, "Delete",Toast.LENGTH_SHORT).show();
//            removeItem(recipe);

        }
        if(idd==R.id.upload){
            Toast.makeText(this, "Upload to Recipe Hub",Toast.LENGTH_SHORT).show();

        }
        if(idd==R.id.socialMedia){
            Toast.makeText(this, "Share on Social Media",Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }



}



