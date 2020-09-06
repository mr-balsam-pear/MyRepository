package com.balsam.cache.mapper;

import com.balsam.cache.entity.Department;
import com.balsam.cache.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserMapper {
    @Select("select * from user where id=#{id} ")
    User findById(Long id);
}
