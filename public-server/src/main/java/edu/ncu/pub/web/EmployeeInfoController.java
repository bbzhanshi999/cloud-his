package edu.ncu.pub.web;

import edu.ncu.commons.model.EmployeeInfo;
import edu.ncu.pub.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmployeeInfoController {

    @Autowired
    public EmployeeService employeeService;

    @RequestMapping("/login")
    public String login(@RequestBody EmployeeInfo condition, HttpServletResponse response){
        EmployeeInfo employeeInfo = employeeService.authLogin(condition);
        if(employeeInfo==null){
            response.setStatus(403);
            return "failed";
        }
        response.setHeader("eid",employeeInfo.getId());
        response.setHeader("username",employeeInfo.getUsername());
        return "success";
    }

}
