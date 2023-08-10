package com.rmk.demo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class EmployeeSvc {
    @Autowired EmployeeRepo employeeRepo;
    public List<Employee> getAll(){
        return this.employeeRepo.findAll();
    }

    public Employee add(Employee e){
        log.info("Adding emp: {}", e);
        return this.employeeRepo.saveAndFlush(e);
    }

    public Employee update(Employee e){
        return this.employeeRepo.saveAndFlush(e);
    }

    public boolean delete(Long id){
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        this.employeeRepo.findById(id)
                .ifPresentOrElse(
                        employee -> {this.employeeRepo.delete(employee); isDeleted.set(true);}
                        , () -> isDeleted.set(false));

        return isDeleted.get();
    }
}
