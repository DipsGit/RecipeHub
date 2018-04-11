package dipp3.manukau.co.nz.recipe_hub.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import dipp3.manukau.co.nz.recipe_hub.Activities.RecipeItemActivity;
import dipp3.manukau.co.nz.recipe_hub.R;
import dipp3.manukau.co.nz.recipe_hub.Models.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipes;
    private Context context;
    private ArrayList<String> images;

    public RecipeAdapter(List<Recipe> recipes, Context context, ArrayList<String> images) {
        this.recipes = recipes;
        this.context = context;
        this.images = images;
    }

//    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        initImageBitmaps();

        final Recipe recipe = recipes.get(position);
        Glide.with(context)
                .asBitmap()
                .load(images.get(position))
                .into(holder.image);
        holder.recipeTitle.setText(recipe.getRecipeName());
        holder.servingSize.setText("Serving Size: " + recipe.getServingSize());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, recipe.getInstructions(),Toast.LENGTH_SHORT).show();

                Intent i = new Intent(context, RecipeItemActivity.class);
                i.putExtra("img_url",images.get(position));
                i.putExtra("recipe_name",recipe.getRecipeName());
                i.putExtra("category",recipe.getCategory());
                i.putExtra("serving_size",recipe.getServingSize());
                i.putExtra("prep_time",recipe.getPrepTime());
                i.putExtra("cook_time",recipe.getCookTime());
                i.putExtra("ingredients",recipe.getIngredients());
                i.putExtra("instructions",recipe.getInstructions());
                i.putExtra("id", recipe.getId());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView recipeTitle, servingSize, category, prepTime, cookTime, ingredients, instructions, id;
        CircleImageView image;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            recipeTitle = itemView.findViewById(R.id.recipeTitleTxtView);
            servingSize = itemView.findViewById(R.id.servingSizeTxtView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    public void filterList(ArrayList<Recipe> filteredRecipes) {
        this.recipes = filteredRecipes;
        notifyDataSetChanged();
    }
    //Initiates Bitmap Image
    private void initImageBitmaps(){
        images.add("http://supplies.thesmartteacher.com.s3.amazonaws.com/assets/exchange/Ms%20J%20Food%20Face.jpg");
        //Reference here: http://www.thesmartteacher.com/exchange/resource/226/Digital-Acrimboldo-Food-Faces-Using-Free-Online-Software
    }

//    public void removeItem(Recipe recipe){
//        int position = recipes.indexOf(recipe);
//        recipes.remove(position);
//        notifyItemRemoved(position);
//    }
}



