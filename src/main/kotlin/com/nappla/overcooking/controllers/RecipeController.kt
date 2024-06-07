package com.nappla.overcooking.controller

import com.nappla.overcooking.domain.entity.RecipeEntity
import com.nappla.overcooking.repository.RecipeRepository
import com.nappla.overcooking.service.RecipeService
import lombok.extern.java.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import kotlin.time.Duration

@RestController
@Log
@RequestMapping("/recipes")
class RecipeController {
    lateinit var recipeService: RecipeService
    @GetMapping("")
    fun retrieveAllRecipes(): List<RecipeEntity> {
        return recipeService.listRecipes()
    }
}