package com.nappla.overcooking.repository

import com.nappla.overcooking.domain.entity.RecipeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import kotlin.time.Duration


@Repository
interface RecipeRepository : JpaRepository<RecipeEntity, Long> {

    fun cookTimeLessThan(timeValue: Duration): List<RecipeEntity>

    fun prepTimeLessThan(timeValue: Duration): List<RecipeEntity>

    fun totalTimeLessThan(timeValue: Duration): List<RecipeEntity>

    fun findByNameContainingIgnoreCase(keyword: String?): List<RecipeEntity>

    fun findByRecipeIngredientPartsContainingIgnoreCase(keyword: String?): List<RecipeEntity>

}