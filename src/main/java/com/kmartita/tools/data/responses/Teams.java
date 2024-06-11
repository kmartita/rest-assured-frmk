package com.kmartita.tools.data.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Teams {

    @JsonProperty("teams")
    private List<Team> teams;
}
