package com.workintech.spring17challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade {
    private Integer coefficient;
    private String note;
}
