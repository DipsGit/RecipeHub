package dipp3.manukau.co.nz.recipe_hub.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dipp3.manukau.co.nz.recipe_hub.Models.Recipe;

@Database(entities = {Recipe.class}, version = 3)
public abstract class MyDatabase extends RoomDatabase{
    public abstract Dao dao();
}
