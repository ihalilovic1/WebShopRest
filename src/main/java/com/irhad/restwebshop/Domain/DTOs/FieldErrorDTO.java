package com.irhad.restwebshop.Domain.DTOs;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class FieldErrorDTO {
    private String field;
    private String message;
}
