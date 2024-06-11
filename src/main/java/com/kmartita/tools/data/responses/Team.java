package com.kmartita.tools.data.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kmartita.tools.data.HasId;
import lombok.Data;

import java.util.List;

@Data
public class Team implements HasId {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("color")
    private String color;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("members")
    private List<Member> members;

    @Data
    public static class Member {
        @JsonProperty("user")
        private User user;
    }

    @Data
    public static class User {
        @JsonProperty("id")
        private int id;

        @JsonProperty("username")
        private String username;

        @JsonProperty("email")
        private String email;

        @JsonProperty("color")
        private String color;

        @JsonProperty("profilePicture")
        private Object profilePicture;

        @JsonProperty("initials")
        private String initials;

        @JsonProperty("role")
        private Integer role;

        @JsonProperty("custom_role")
        private Object customRole;

        @JsonProperty("last_active")
        private String lastActive;

        @JsonProperty("date_joined")
        private String dateJoined;

        @JsonProperty("date_invited")
        private String dateInvited;
    }
}
