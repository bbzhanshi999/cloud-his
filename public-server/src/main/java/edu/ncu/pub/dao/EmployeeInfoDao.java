package edu.ncu.pub.dao;

import edu.ncu.commons.dao.BaseDao;
import edu.ncu.commons.model.EmployeeInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInfoDao extends BaseDao<EmployeeInfo> {

    @Override
    //@Cacheable(cacheNames = "employees",key="#condition.username",unless = "#result ==null")
    EmployeeInfo find(EmployeeInfo condition);
}
