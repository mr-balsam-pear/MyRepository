package com.balsam.cache.mapper;

import com.balsam.cache.entity.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IDepartmentMapper {
    @Select("select * from department where id=#{id} ")
    Department findById(Long id);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    void updateDep(Department department);

    @Insert("insert into department(departmentName) values(#{departmentName)")
    void insertDep(Department department);

    @Delete("delete from department where id=#{id}")
    void deleteById(Long id);
}
