package com.rmk.demo1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long empId;
    @JsonProperty(value = "fName")
    String fName;
    @JsonProperty(value = "lName")
    String lName;
    Double salary;
    String grade;
    String isContractor;
}
