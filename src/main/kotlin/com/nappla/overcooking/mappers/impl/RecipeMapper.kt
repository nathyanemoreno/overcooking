package com.nappla.overcooking.mappers.impl

import com.nappla.overcooking.domain.dto.RecipeDTO
import com.nappla.overcooking.domain.entities.RecipeEntity
import com.nappla.overcooking.mappers.Mapper

class RecipeMapper : Mapper<RecipeEntity?, RecipeDTO?> {
    var `val`: lateinit? = null
    override fun mapTo(recipeEntity: RecipeEntity): RecipeDTO {
        return null
    }

    override fun mapFrom(recipeDTO: RecipeDTO): RecipeEntity {
        return null
    }
}
