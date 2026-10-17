package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

// Data class: stores information about one recipe.
data class Recipe(
    val name: String,
    val category: String,
    val difficulty: String,
    val ingredients: List<String>
)

// Class: manages a collection of recipes.
class RecipeLibrary {
    // SnapshotStateList automatically notifies Compose when a recipe is added.
    private val recipes = mutableStateListOf(
        Recipe(
            name = "Tacos",
            category = "Dinner",
            difficulty = "Easy",
            ingredients = listOf("Tortillas", "Beef", "Cheese")
        ),
        Recipe(
            name = "Pancakes",
            category = "Breakfast",
            difficulty = "Medium",
            ingredients = listOf("Flour", "Eggs", "Milk")
        )
    )

    fun getRecipes(): List<Recipe> = recipes

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    // Function using when.
    fun difficultyMessage(difficulty: String): String {
        return when (difficulty) {
            "Easy" -> "This recipe is beginner-friendly."
            "Medium" -> "This recipe requires some practice."
            "Hard" -> "This recipe is for experienced cooks."
            else -> "Difficulty is unknown."
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyFirstAppTheme {
                RecipeLibraryScreen()
            }
        }
    }
}

@Composable
fun RecipeLibraryScreen() {
    val library = remember { RecipeLibrary() }
    val screenTitle = "My Recipe Library"

    var recipeName by remember { mutableStateOf("") }
    var recipeCategory by remember { mutableStateOf("") }
    var recipeDifficulty by remember { mutableStateOf("") }
    var recipeIngredients by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = screenTitle)

            OutlinedTextField(
                value = recipeName,
                onValueChange = { recipeName = it },
                label = { Text("Recipe name") },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            OutlinedTextField(
                value = recipeCategory,
                onValueChange = { recipeCategory = it },
                label = { Text("Category") },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            OutlinedTextField(
                value = recipeDifficulty,
                onValueChange = { recipeDifficulty = it },
                label = { Text("Difficulty (Easy, Medium, or Hard)") },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            OutlinedTextField(
                value = recipeIngredients,
                onValueChange = { recipeIngredients = it },
                label = { Text("Ingredients, separated by commas") },
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Button(
                enabled = recipeName.isNotBlank() && recipeCategory.isNotBlank(),
                onClick = {
                    val ingredients = recipeIngredients
                        .split(",")
                        .map { it.trim() }
                        .filter { it.isNotBlank() }

                    library.addRecipe(
                        Recipe(
                            name = recipeName.trim(),
                            category = recipeCategory.trim(),
                            difficulty = recipeDifficulty.trim().ifBlank { "Unknown" },
                            ingredients = ingredients
                        )
                    )

                    recipeName = ""
                    recipeCategory = ""
                    recipeDifficulty = ""
                    recipeIngredients = ""
                }
            ) {
                Text("Save Recipe")
            }

            LazyColumn {
                items(library.getRecipes()) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        difficultyMessage = library.difficultyMessage(recipe.difficulty)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, difficultyMessage: String) {
    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Text(text = recipe.name)
        Text(text = "Category: ${recipe.category}")
        Text(text = "Difficulty: ${recipe.difficulty}")
        Text(text = difficultyMessage)

        for (ingredient in recipe.ingredients) {
            Text(text = "• $ingredient")
        }
    }
}
