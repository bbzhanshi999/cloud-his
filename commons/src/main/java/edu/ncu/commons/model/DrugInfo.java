package edu.ncu.commons.model;

import lombok.Data;

@Data
public class DrugInfo extends BaseModel {
    private String drugCode;
    private String drugName;
    private String unit;
    private Double price;

}
