package com.workintech.spring17challenge.entity;

import lombok.Data;

@Data
public class Course {

    private Integer id;
    private String name;
    private Integer credit;
    private Grade grade;
}
