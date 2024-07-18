package com.kmartita.tools.data.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kmartita.tools.data.HasId;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Space implements HasId {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}
