package com.example.exercisenine.entity.enumiration;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Администратор"),
    OPERATOR("Оператор"),
    METER("Счётчик"),
    BLOCK("Заблокированный");
    private final String displayName;

    UserRole(String displayName){
        this.displayName=displayName;
    }
}
