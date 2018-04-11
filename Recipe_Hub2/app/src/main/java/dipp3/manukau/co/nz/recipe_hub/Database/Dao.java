package dipp3.manukau.co.nz.recipe_hub.Database;


import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import dipp3.manukau.co.nz.recipe_hub.Models.Recipe;

@android.arch.persistence.room.Dao
public interface Dao {
//   Inserting Data into the database.
    @Insert
    void addRecipe(Recipe... recipes);
//
    @Query("select id, recipeName, servingSize from Recipes ORDER BY id")
   List<Recipe> getRecipes();

    @Query("select id, recipeName, servingSize, category, prepTime, cookTime, ingredients, instructions from Recipes where category='Breakfast'")
    List<Recipe> getRecipesBreakfast();

    @Query("select id, recipeName, servingSize, category, prepTime, cookTime, ingredients, instructions from Recipes where category='Lunch'")
    List<Recipe> getRecipesLunch();

    @Query("select id, recipeName, servingSize, category, prepTime, cookTime, ingredients, instructions from Recipes where category='Dinner'")
    List<Recipe> getRecipesDinner();

    @Query("select id, recipeName, servingSize, category, prepTime, cookTime, ingredients, instructions from Recipes where category='Snacks'")
    List<Recipe> getRecipesSnacks();

    @Query("DELETE FROM Recipes")
    public void nukeTable();

    @Delete
    public void deleteRecipe(Recipe recipe);

}
