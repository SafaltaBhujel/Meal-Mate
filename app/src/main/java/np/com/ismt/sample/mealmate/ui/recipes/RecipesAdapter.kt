package np.com.ismt.sample.mealmate.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import np.com.ismt.sample.mealmate.helpers.HelperUtil
import np.com.ismt.sample.mealmate.databinding.LayoutRecipeItemBinding
import np.com.ismt.sample.mealmate.models.Recipe

class RecipesAdapter(options: FirestoreRecyclerOptions<Recipe>):
    FirestoreRecyclerAdapter<Recipe, RecipesAdapter.RecipeHolder>(options) {

    class RecipeHolder(
        private val binding: LayoutRecipeItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindRecipe(recipe: Recipe) {
            recipe.image?.apply {
                binding.ivRecipeImage.setImageBitmap(
                    HelperUtil.base64toBitmap(
                        this,
                        binding.root.context.applicationContext
                    )
                )
            }
            binding.tvRecipeName.text = recipe.name
            binding.tvFoodCategory.text = recipe.category
            binding.tvCookingTime.text = recipe.cookingTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutRecipeItemBinding.inflate(layoutInflater, parent, false)
        return RecipeHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int, recipe: Recipe) {
        holder.bindRecipe(recipe)
    }
}