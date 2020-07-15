package edu.ncu.commons.model;

import lombok.Data;

@Data
public class DepartInfo extends BaseModel {
    /*
    * depart_name varchar(32) null,
	depart_code varchar(32) null,*/

    private String departName;
    private String departCode;
}
