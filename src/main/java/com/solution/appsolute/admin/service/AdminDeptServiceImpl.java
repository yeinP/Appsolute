package com.solution.appsolute.admin.service;


import com.solution.appsolute.admin.dao.repository.AdminDeptRepository;
import com.solution.appsolute.admin.dao.repository.AdminEmployeeRepository;
import com.solution.appsolute.admin.dto.DeptDto;
import com.solution.appsolute.admin.dto.PageRequestDto;
import com.solution.appsolute.admin.dto.PageResultDto;
import com.solution.appsolute.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@Service
public class AdminDeptServiceImpl implements AdminDeptService {
    @Autowired
    AdminEmployeeRepository adminEmployeeRepository;

    @Autowired
    AdminDeptRepository adminDeptRepository;

    @Override
    public Long registerDept(DeptDto deptDto) {
        Dept entity = deptDtoToEntity(deptDto);
        adminDeptRepository.save(entity);
        return entity.getDeptNo();
    }
    @Override
    public DeptDto readDept(Long deptNo) {
        Optional<Dept> result = adminDeptRepository.findById(deptNo);
        return result.isPresent() ? entityToDeptDto(result.get()) : null;
    }

    @Override
    public void modifyDept(DeptDto deptDto) {
        Optional<Dept> result = adminDeptRepository.findById(deptDto.getDeptNo());
        if(result.isPresent()) {
            Dept entity = result.get();
            entity.changeDeptName(deptDto.getDeptName());
            entity.changeDeptBranch(deptDto.getDeptBranch());
            entity.changeDeptLoc(deptDto.getDeptLoc());
            adminDeptRepository.save(entity);
        }
    }

    @Override
    public void removeDept(Long deptNo) {
        adminDeptRepository.deleteById(deptNo);
    }



    @Override
    public PageResultDto<DeptDto, Dept> getDeptList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("deptNo").descending());
        Page<Dept> result = adminDeptRepository.findAll(pageable);
        Function<Dept, DeptDto> fn = (entity -> entityToDeptDto(entity));
        return new PageResultDto<>(result, fn);
    }


}
