package dipp3.manukau.co.nz.recipe_hub.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import dipp3.manukau.co.nz.recipe_hub.Activities.MainActivity;
import dipp3.manukau.co.nz.recipe_hub.Database.MyDatabase;
import dipp3.manukau.co.nz.recipe_hub.R;
import dipp3.manukau.co.nz.recipe_hub.Models.Recipe;
import dipp3.manukau.co.nz.recipe_hub.Adapters.RecipeAdapter;

public class CategoryTabFragmentSnacks extends Fragment{
    private static final String TAG = "CategoryTabFragmentSnacks";

    RecyclerView recyclerView;
    EditText search;
    RecipeAdapter adapter;
    List<Recipe> recipes = MainActivity.myDatabase.dao().getRecipesSnacks();
    ArrayList<String> imageUrls = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_snacks_layout, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        search = view.findViewById(R.id.search);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecipeAdapter(recipes, getContext(), imageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        //adding a TextChangedListener
        //to call a method whenever there is some change on the EditText
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });


        return view;
    }


    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Recipe> filterdRecipes = new ArrayList<>();

        //looping through existing elements
        for (Recipe r : recipes) {
            //if the existing elements contains the search input
            if (r.getRecipeName().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdRecipes.add(r);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdRecipes);    }



}


