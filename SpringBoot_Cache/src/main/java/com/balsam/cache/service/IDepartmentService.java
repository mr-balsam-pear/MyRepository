package com.balsam.cache.service;

import com.balsam.cache.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IDepartmentService {
    Department findById(Long id);

    void updateDep(Department department);

    void insertDep(Department department);

    void deleteById(Long id);
}
