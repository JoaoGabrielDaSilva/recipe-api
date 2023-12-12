package com.joao.recipeapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreatePostRequestBody {

    @NotEmpty
    private String description;

    @NotEmpty
    private Integer authorId;
}
