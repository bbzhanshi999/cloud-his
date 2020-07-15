package edu.ncu.pub.web;

import edu.ncu.commons.model.DepartInfo;
import edu.ncu.pub.service.DepartInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depart")
public class DepartInfoController {

    @Autowired
    private DepartInfoService departInfoService;

    @PostMapping("find")
    public List<DepartInfo> findList(@RequestBody DepartInfo condition){
        return departInfoService.findList(condition);
    }
}
