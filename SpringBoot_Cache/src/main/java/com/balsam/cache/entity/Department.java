package com.balsam.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private Long id;
    private String departmentName;

    private List<User> users;

}
