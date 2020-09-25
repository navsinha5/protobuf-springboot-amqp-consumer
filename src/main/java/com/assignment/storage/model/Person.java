package com.assignment.storage.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Person {
    private String id;
    @NotBlank(message= "missing parameter, name is required")
    private String name;
    @NotBlank(message= "missing parameter, dob is required")
    private String dob;
    @NotBlank(message= "missing parameter, salary is required")
    private String salary;
    @NotNull(message = "missing parameter, age is required")
    @Min(value = 0, message= "age can't be negative integer")
    @Max(value = 100, message = "age can't be greater than 100")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
