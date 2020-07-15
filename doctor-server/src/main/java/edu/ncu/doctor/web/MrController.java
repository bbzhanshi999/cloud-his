package edu.ncu.doctor.web;

import edu.ncu.commons.model.MedicalRecord;
import edu.ncu.doctor.pojo.MrDrugInfo;
import edu.ncu.doctor.service.MrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mr")
public class MrController {

    @Autowired
    private MrService mrService;

    @GetMapping("find/{id}")
    public MedicalRecord find(@PathVariable String id){
        return mrService.findById(id);
    }

    @PostMapping("create")
    public String createMr(@RequestBody MrDrugInfo mrDrugInfo){
        mrService.createMrDrugInfo(mrDrugInfo);
        return "success";
    }
}
