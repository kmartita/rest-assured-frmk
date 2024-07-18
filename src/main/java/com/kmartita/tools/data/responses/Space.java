package com.kmartita.tools.data.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kmartita.tools.data.HasId;
import lombok.Data;

import java.util.List;

@Data
public class Space implements HasId {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("color")
    private String color;

    @JsonProperty("private")
    private Boolean isPrivate;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("admin_can_manage")
    private Boolean adminCanManage;

    @JsonProperty("statuses")
    private List<Status> statuses;

    @JsonProperty("multiple_assignees")
    private Boolean multipleAssignees;

    @JsonIgnore
    @JsonProperty("features")
    private Features features;

    @JsonProperty("archived")
    private Boolean archived;

    @JsonIgnore
    @JsonProperty("members")
    private List<Member> members;

    @Data
    public static class Status {
        @JsonProperty("id")
        private String id;

        @JsonProperty("status")
        private String status;

        @JsonProperty("type")
        private String type;

        @JsonProperty("orderindex")
        private Integer orderIndex;

        @JsonProperty("color")
        private String color;
    }

    @Data
    public static class Features {
        @JsonProperty("due_dates")
        private DueDates dueDates;

        @JsonProperty("sprints")
        private Sprints sprints;

        @JsonProperty("time_tracking")
        private TimeTracking timeTracking;

        @JsonProperty("points")
        private Points points;

        @JsonProperty("custom_items")
        private CustomItems customItems;

        @JsonProperty("priorities")
        private Priorities priorities;

        @JsonProperty("tags")
        private Tags tags;

        @JsonProperty("time_estimates")
        private TimeEstimates timeEstimates;

        @JsonProperty("check_unresolved")
        private CheckUnresolved checkUnresolved;

        @JsonProperty("zoom")
        private Zoom zoom;

        @JsonProperty("milestones")
        private Milestones milestones;

        @JsonProperty("custom_fields")
        private CustomFields customFields;

        @JsonProperty("remap_dependencies")
        private RemapDependencies remapDependencies;

        @JsonProperty("dependency_warning")
        private DependencyWarning dependencyWarning;

        @JsonProperty("status_pies")
        private StatusPies statusPies;

        @JsonProperty("multiple_assignees")
        private MultipleAssignees multipleAssignees;

        @JsonProperty("emails")
        private Emails emails;
    }

    @Data
    public static class DueDates {
        @JsonProperty("enabled")
        private Boolean enabled;

        @JsonProperty("start_date")
        private Boolean startDate;

        @JsonProperty("remap_due_dates")
        private Boolean remapDueDates;

        @JsonProperty("remap_closed_due_date")
        private Boolean remapClosedDueDate;
    }

    @Data
    public static class Sprints {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class Points {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class CustomItems {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class Priorities {
        @JsonProperty("enabled")
        private Boolean enabled;

        @JsonProperty("priorities")
        private List<Priority> priorities;

        @Data
        public static class Priority {
            @JsonProperty("color")
            private String color;

            @JsonProperty("id")
            private String id;

            @JsonProperty("orderindex")
            private String orderIndex;

            @JsonProperty("priority")
            private String priority;
        }
    }

    @Data
    public static class Tags {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class TimeEstimates {
        @JsonProperty("enabled")
        private Boolean enabled;

        @JsonProperty("rollup")
        private Boolean rollup;

        @JsonProperty("per_assignee")
        private Boolean perAssignee;
    }

    @Data
    public static class CheckUnresolved {
        @JsonProperty("enabled")
        private Boolean enabled;

        @JsonProperty("subtasks")
        private Object subtasks;

        @JsonProperty("checklists")
        private Object checklists;

        @JsonProperty("comments")
        private Object comments;
    }

    @Data
    public static class Zoom {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class Milestones {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class CustomFields {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class RemapDependencies {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class DependencyWarning {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class StatusPies {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class MultipleAssignees {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class Emails {
        @JsonProperty("enabled")
        private Boolean enabled;
    }

    @Data
    public static class TimeTracking {
        @JsonProperty("enabled")
        private Boolean enabled;

        @JsonProperty("harvest")
        private Boolean harvest;

        @JsonProperty("rollup")
        private Boolean rollup;

        @JsonProperty("default_to_billable")
        private Integer defaultToBillable;
    }

    @Data
    public static class Member {
        @JsonProperty("id")
        private String id;
    }
}
