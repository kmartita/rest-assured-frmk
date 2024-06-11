package com.kmartita.tools.data.responses;

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

    @JsonProperty("features")
    private Features features;

    @JsonProperty("archived")
    private Boolean archived;

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

        @JsonProperty("points")
        private Points points;

        @JsonProperty("custom_items")
        private CustomItems customItems;

        @JsonProperty("tags")
        private Tags tags;

        @JsonProperty("time_estimates")
        private TimeEstimates timeEstimates;

        @JsonProperty("checklists")
        private Checklists checklists;

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

        @JsonProperty("portfolios")
        private Portfolios portfolios;

        @JsonProperty("emails")
        private Emails emails;

        @JsonProperty("time_tracking")
        private TimeTracking timeTracking;
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
    public static class Checklists {
        @JsonProperty("enabled")
        private Boolean enabled;
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
    public static class Portfolios {
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
        @JsonProperty("default_to_billable")
        private Integer defaultToBillable;
    }
}

