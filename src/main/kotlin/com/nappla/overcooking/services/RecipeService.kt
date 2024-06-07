package com.nappla.overcooking.service

import com.nappla.overcooking.domain.dto.RecipeDTO

interface RecipeService {
    fun listRecipes(): List<RecipeDTO>

}