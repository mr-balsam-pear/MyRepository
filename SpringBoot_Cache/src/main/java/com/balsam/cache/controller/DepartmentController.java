package com.balsam.cache.controller;

import com.balsam.cache.entity.Department;
import com.balsam.cache.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("findOne")
    public ResponseEntity<Department> findOne(@RequestParam(value = "id") Long id) {
        Department de = departmentService.findById(id);
        return ResponseEntity.ok(de);
    }

    @GetMapping("update")
    public ResponseEntity<Department> update(Department department){
        departmentService.updateDep(department);
         return ResponseEntity.ok().body(null);
    }
}
