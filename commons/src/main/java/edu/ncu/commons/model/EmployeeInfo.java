package edu.ncu.commons.model;

import lombok.Data;

@Data
public class EmployeeInfo extends BaseModel {
    /**
     * username varchar(32) null,
     * 	password varchar(32) null,
     * 	name varchar(32) null,
     * 	depart_id varchar(32) null,
     * 	phone varchar(20) null,
     */
    private String username;
    private String password;
    private String name;
    private DepartInfo departInfo;
    private String phone;
}
