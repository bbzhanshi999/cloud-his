package edu.ncu.pub.service;

import edu.ncu.commons.model.EmployeeInfo;
import edu.ncu.commons.service.BaseService;
import edu.ncu.commons.utils.IdGen;
import edu.ncu.pub.dao.EmployeeInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "employees") //说明当前类操作的cachenname都是employees
public class EmployeeService extends BaseService<EmployeeInfoDao, EmployeeInfo> {

    @Autowired
    PasswordEncoder passwordEncoder;

   /* @Override
    @Cacheable(key="#condition.username",unless = "#result ==null")
    public EmployeeInfo find(EmployeeInfo condition) {
        return super.find(condition);
    }*/

    /**
     * 每次插入或者更新数据之时，根据key删除原来的缓存数据
     * @param employeeInfo
     * @param idGen
     */
    @Override
    @CacheEvict(key="#employeeInfo.username")
    public void save(EmployeeInfo employeeInfo, IdGen idGen) {
        super.save(employeeInfo, idGen);
    }

    /**
     * 验证登录是否成功
     * @param condition
     * @return
     */
    public EmployeeInfo authLogin(EmployeeInfo condition){
        EmployeeInfo employeeInfo = dao.find(condition);
        if(employeeInfo==null) return null;
        //对前台传过来的密码进行加密，和查出来的密码进行比对
        return passwordEncoder.matches(condition.getPassword(),employeeInfo.getPassword())?employeeInfo:null;
    }
}
