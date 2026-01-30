package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private NameDto name;
    private String gender;

    @Override
    public String toString() {
        if (Objects.isNull(name)) return "";
        return String.format("%s %s %s, %s", name.getTitle(), name.getFirst(), name.getLast(), gender);
    }
}
