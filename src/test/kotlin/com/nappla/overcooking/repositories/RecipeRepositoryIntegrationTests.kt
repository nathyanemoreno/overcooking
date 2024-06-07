package com.nappla.overcooking.repository

import assertk.assertThat
import assertk.assertions.*
import com.nappla.overcooking.TestDataUtil
import com.nappla.overcooking.domain.entity.RecipeEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*
import kotlin.time.Duration

@SpringBootTest
@ExtendWith(SpringExtension::class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RecipeRepositoryIntegrationTests {

    @Autowired
    private lateinit var underTest: RecipeRepository
    @Test
    fun testThatRecipeCanBeCreatedAndRecalled(){
        val recipe = TestDataUtil.createRecipeA()
        underTest.save(recipe)

        val result: Optional<RecipeEntity> = underTest.findById(recipe.id)

        assertThat(result).isPresent()
        assertThat(result.get()).isEqualTo(recipe)
    }
    @Test
    fun testThatRecipeCanBeUpdated(){
        val recipe = TestDataUtil.createRecipeA()
        underTest.save(recipe)

        recipe.authorName = "Maria from Brazil"

        underTest.save(recipe)

        val result: Optional<RecipeEntity> = underTest.findById(recipe.id)

        assertThat(result).isPresent()
        assertThat(result.get()).isEqualTo(recipe)
    }
    @Test
    fun testThatRecipeCanBeDeleted(){
        val recipe = TestDataUtil.createRecipeA()
        underTest.save(recipe)

        underTest.delete(recipe)

        val result: Optional<RecipeEntity> = underTest.findById(recipe.id)

        assertThat(result).isEmpty()
    }
    @Test
    fun testThatMultipleRecipesCanBeCreatedAndRecalled(){
        val recipeA: RecipeEntity = TestDataUtil.createRecipeA()
        underTest.save(recipeA)

        val recipeB: RecipeEntity = recipeA.copy(id = 2)
        underTest.save(recipeB)

        val recipeC: RecipeEntity = recipeA.copy(id = 3)
        underTest.save(recipeC)

        val results: List<RecipeEntity> = underTest.findAll()

        assertThat(results).hasSize(3)
        assertThat(results).containsExactly(recipeA, recipeB, recipeC)
    }

    @Test
    fun testThatGetRecipeGetRecipesWithCookTimeLessThan(){
        val recipeA: RecipeEntity = TestDataUtil.createRecipeA();
        underTest.save(recipeA)

        val recipeB: RecipeEntity = recipeA.copy(id = 2, cookTime = Duration.parse("PT20M"))
        underTest.save(recipeB)

        val recipeC: RecipeEntity = recipeA.copy(id = 3, cookTime = Duration.parse("PT50M"))
        underTest.save(recipeC)

        val results: List<RecipeEntity> = underTest.cookTimeLessThan(Duration.parse("PT30M"))

        assertThat(results).containsExactly(recipeA, recipeB)
    }

    @Test
    fun testThatGetRecipeGetRecipesWithNameContaining(){
        val recipeA: RecipeEntity = TestDataUtil.createRecipeA();
        underTest.save(recipeA)

        val recipeB: RecipeEntity = recipeA.copy(id = 2, name = "Chicken Breast")
        underTest.save(recipeB)

        val recipeC: RecipeEntity = recipeA.copy(id = 3, name = "Chicken Teriyaki")
        underTest.save(recipeC)

        val results: List<RecipeEntity> = underTest.findByNameContainingIgnoreCase("chi")

        assertThat(results).containsExactly(recipeB, recipeC)
    }

    @Test
    fun testThatGetRecipeGetRecipesWithRecipeIngredientParts(){
        val recipeA: RecipeEntity = TestDataUtil.createRecipeA();
        underTest.save(recipeA)

        val recipeB: RecipeEntity = recipeA.copy(id = 2, recipeIngredientParts = "c(\"blueberries\", \"granulated sugar\", \"vanilla yogurt\", \"lemon juice\")")
        underTest.save(recipeB)

        val recipeC: RecipeEntity = recipeA.copy(id = 3, recipeIngredientParts = "c(\"sugar\", \"lemons, rind of\", \"lemon, zest of\", \"fresh water\", \"fresh lemon juice\")")
        underTest.save(recipeC)

        val results: List<RecipeEntity> = underTest.findByRecipeIngredientPartsContainingIgnoreCase("lemon")

        assertThat(results).containsExactly(recipeB, recipeC)
    }
}