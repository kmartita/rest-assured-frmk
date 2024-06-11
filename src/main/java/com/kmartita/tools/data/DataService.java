package com.kmartita.tools.data;

import lombok.Getter;

import java.util.Objects;

@Getter
public class DataService<Response> {

    private final Entity entity;
    private final Response response;

    public DataService(Entity entity, Response id) {
        this.entity = entity;
        this.response = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataService<?> data = (DataService<?>) o;
        return entity == data.entity && Objects.equals(response, data.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, response);
    }

    @Override
    public String toString() {
        return String.format("Data[entity=%s, response=%s", entity, response);
    }

}
