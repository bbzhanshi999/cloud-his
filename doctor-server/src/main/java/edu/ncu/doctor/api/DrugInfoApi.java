package edu.ncu.doctor.api;

import edu.ncu.commons.model.DrugInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "public-server")
public interface DrugInfoApi {

    @RequestMapping(value = "/drug/find",method = RequestMethod.POST)
    List<DrugInfo> findList(DrugInfo condition);
}
