package com.bridgelabz.builder;

import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Purpose : This builder class which holds all the building related application
 *
 * @author SREELIPTA
 * @since 10-12-2021
 */
@Component
public class EmployeePayrollBuilder {

    /**
     * Purpose : This method is created to copy the properties of simple POJO to @Entity class
     *
     * @param employeeDto    : This the POJO class's object which is the source for copying the properties
     * @param employeeEntity : This is the Entity class's object which is the destination for that copied properties
     * @return the object of Employee class
     */
    public EmployeeEntity buildEmployeeEntity(EmployeeDto employeeDto, EmployeeEntity employeeEntity) {
        BeanUtils.copyProperties(employeeDto, employeeEntity);
        return employeeEntity;
    }
}
