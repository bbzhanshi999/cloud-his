package edu.ncu.commons.model;

import lombok.Data;

import java.util.Date;

@Data
public class MedicalRecord extends BaseModel {

    private String name;
    private String gender;
    private Integer age;
    private String contract;
    private String payStatus;
    private Date payTime;
    private Double amount;
    private Double selfPaying;

    private EmployeeInfo doctor;
    private EmployeeInfo operator;
    private DepartInfo departInfo;
}
