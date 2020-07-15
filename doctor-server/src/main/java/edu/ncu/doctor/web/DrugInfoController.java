package edu.ncu.doctor.web;

import edu.ncu.commons.model.DrugInfo;
import edu.ncu.doctor.api.DrugInfoApi;
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
    private DrugInfoApi drugInfoApi;

    /**
     * 通过feign获取药品信息
     * @return
     */
    @PostMapping("find")
    public List<DrugInfo> findRemoteList(@RequestBody DrugInfo condition){
        return drugInfoApi.findList(condition);
    }

}
