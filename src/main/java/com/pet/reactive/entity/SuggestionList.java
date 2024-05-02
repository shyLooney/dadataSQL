package com.pet.reactive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SuggestionList {
    @Id
    @JsonIgnore
    private Long id;
    private List<Suggestion> suggestions;

}
