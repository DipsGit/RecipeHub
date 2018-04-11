package dipp3.manukau.co.nz.recipe_hub.Activities;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import dipp3.manukau.co.nz.recipe_hub.Adapters.SectionsPageAdapter;
import dipp3.manukau.co.nz.recipe_hub.Helpers.BottomNavigationViewHelper;
import dipp3.manukau.co.nz.recipe_hub.R;
import dipp3.manukau.co.nz.recipe_hub.Models.Recipe;

public class AddRecipeActivity extends AppCompatActivity {
    private static final String TAG = "AddRecipeActivity";
    EditText chooseTimePrep;
    EditText chooseTimeCook;
    private ViewPager mViewPager;
    private Button recipeAddBtn;
    private EditText recipeNameEditText, servingSizeEditText, ingredientsEditText, instructionsEditText;
    private Spinner categorySpinner;
    private TextInputLayout layoutCategory, layoutRecipeName, layoutServingSize, layoutPrepTime, layoutCookTime, layoutIngredients, layoutInstructions;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


//Prep TimePicker Start
        chooseTimePrep = findViewById(R.id.prepTimeEditText);
        chooseTimePrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialogPrep = new TimePickerDialog(AddRecipeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        chooseTimePrep.setText(hourOfDay + " Hours " + minutes + " Minutes");
                    }
                }, 0, 0, true);
                timePickerDialogPrep.show();
            }
        });
//Prep TimePicker End
//Cook TimePicker Start
        chooseTimeCook = findViewById(R.id.cookTimeEditText);
        chooseTimeCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialogCook = new TimePickerDialog(AddRecipeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        chooseTimeCook.setText(hourOfDay + " Hours " + minutes + " Minutes");
                    }
                }, 0, 0, true);
                timePickerDialogCook.show();
            }
        });
//Cook TimePicker End
//Category Spinner Starts Here
        Spinner catSpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddRecipeActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categoriesSpinner)) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(myAdapter);

        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//Category Spinner End
        //db

        initWidgets();
        recipeAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addRecipe();
            }

        });

//Navigation Starts Here
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationViewBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent mainActivityIntent = new Intent(AddRecipeActivity.this, MainActivity.class);
                        startActivity(mainActivityIntent);
                        break;
                    case R.id.ic_myrecipes:
                        Intent myRecipesIntent = new Intent(AddRecipeActivity.this, MyRecipesActivity.class);
                        startActivity(myRecipesIntent);
                        break;
                    case R.id.ic_create:

                        break;
                    case R.id.ic_shared:
                        Intent sharedRecipesIntent = new Intent(AddRecipeActivity.this, SharedRecipesActivity.class);
                        startActivity(sharedRecipesIntent);
                        break;
                }


                return false;
            }
        });
//Navigation Ends Here
    }

    private void initWidgets() {
        layoutCategory = (TextInputLayout) findViewById(R.id.categoryLayout);
        layoutRecipeName = (TextInputLayout) findViewById(R.id.recipeNameLayout);
        layoutServingSize = (TextInputLayout) findViewById(R.id.servingSizeLayout);
        layoutPrepTime = (TextInputLayout) findViewById(R.id.prepTimeLayout);
        layoutCookTime = (TextInputLayout) findViewById(R.id.cookTimeLayout);
        layoutIngredients = (TextInputLayout) findViewById(R.id.ingredientsLayout);
        layoutInstructions = (TextInputLayout) findViewById(R.id.instructionsLayout);


        recipeNameEditText = findViewById(R.id.recipeNameEditText);
        servingSizeEditText = findViewById(R.id.servingSizeEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        instructionsEditText = findViewById(R.id.instructionsEditText);
        chooseTimePrep = findViewById(R.id.prepTimeEditText);
        chooseTimeCook = findViewById(R.id.cookTimeEditText);
        categorySpinner = findViewById(R.id.categorySpinner);
        recipeAddBtn = findViewById(R.id.recipeAddBtn);
    }

    private void addRecipe() {
        boolean isValid = true;
        if (categorySpinner.getSelectedItem().toString().equals("Select a Category...")) {
            layoutCategory.setError("Please choose a Category");
            isValid = false;
        } else {
            layoutCategory.setErrorEnabled(false);
        }
        if (recipeNameEditText.getText().toString().isEmpty()) {
            layoutRecipeName.setError("Please Insert a Recipe Title");
            isValid = false;
        } else {
            layoutRecipeName.setErrorEnabled(false);
        }
        if (servingSizeEditText.getText().toString().isEmpty()) {
            layoutServingSize.setError("Please Insert a Serving Size");
            isValid = false;
        } else {
            layoutServingSize.setErrorEnabled(false);
        }
        if (chooseTimePrep.getText().toString().isEmpty()) {
            layoutPrepTime.setError("Please Insert a Preparation Time");
            isValid = false;
        } else {
            layoutPrepTime.setErrorEnabled(false);
        }
        if (chooseTimeCook.getText().toString().isEmpty()) {
            layoutCookTime.setError("Please Insert a Cooking Time");
            isValid = false;
        } else {
            layoutCookTime.setErrorEnabled(false);
        }
        if (ingredientsEditText.getText().toString().isEmpty()) {
            layoutIngredients.setError("Please Insert some Ingredients");
            isValid = false;
        } else {
            layoutIngredients.setErrorEnabled(false);
        }
        if (instructionsEditText.getText().toString().isEmpty()) {
            layoutInstructions.setError("Please Insert some Instructions");
            isValid = false;
        } else {
            layoutInstructions.setErrorEnabled(false);
        }

        if (isValid){
                            Recipe recipe = new Recipe(recipeNameEditText.getText().toString(), categorySpinner.getSelectedItem().toString(), servingSizeEditText.getText().toString(),
                        chooseTimePrep.getText().toString(), chooseTimeCook.getText().toString(), ingredientsEditText.getText().toString(), instructionsEditText.getText().toString());
                Log.d(TAG, "This: " + recipeNameEditText.getText().toString());

                MainActivity.myDatabase.dao().addRecipe(recipe);
                Toast.makeText(getApplicationContext(), "Recipe Successfully Added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddRecipeActivity.this, MainActivity.class));
        }
        }
    }


