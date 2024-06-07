package com.nappla.overcooking.domain.entity

import jakarta.persistence.*
import java.sql.Timestamp
import kotlin.time.Duration

@Entity
@Table(name = "recipe")
data class RecipeEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_seq")
        val id: Long,
        val recipeId: Long,
        val authorId: Long,
        val name: String,
        var authorName: String,
        var cookTime: Duration,
        var prepTime: Duration,
        val totalTime: Duration,
        val images: String,
        val datePublished: Timestamp = Timestamp(System.currentTimeMillis()),
        val description: String,
        val recipeIngredientQuantities: String,
        val recipeIngredientParts: String,
        val recipeInstructions: String,
        val recipeYield: String,
        val recipeServings: Long,
        val reviewCount: Long = 0,
        val aggregatedRating: Float = 0.0f,

        val recipeCategory: String? = "",
        val keywords: String? = "",
        val calories: Float? = null,
        val fatContent: Float? = null,
        val saturatedFatContent: Float? = null,
        val cholesterolContent: Float? = null,
        val sodiumContent: Float? = null,
        val carbohydrateContent: Float? = null,
        val fiberContent: Float? = null,
        val sugarContent: Float? = null,
        val proteinContent: Float? = null,
)
