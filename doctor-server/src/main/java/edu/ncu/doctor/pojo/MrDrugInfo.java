package edu.ncu.doctor.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MrDrugInfo {

    /**
     * 病历id
     */
    private String mrId;
    /**
     * 总金额
     */
    private Double amount;

    private List<MrDrug> mrDrugs;

}
