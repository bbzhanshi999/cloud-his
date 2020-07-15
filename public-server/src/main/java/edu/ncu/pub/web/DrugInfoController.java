package edu.ncu.pub.web;

import edu.ncu.commons.model.DrugInfo;
import edu.ncu.pub.service.DrugInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drug")
public class DrugInfoController {

    @Autowired
    private DrugInfoService drugInfoService;

    @PostMapping("find")
    public List<DrugInfo> findList(@RequestBody DrugInfo condition){
        return  drugInfoService.findList(condition);
    }
}
