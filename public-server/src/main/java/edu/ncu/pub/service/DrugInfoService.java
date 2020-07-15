package edu.ncu.pub.service;

import edu.ncu.commons.model.DrugInfo;
import edu.ncu.commons.service.BaseService;
import edu.ncu.pub.dao.DrugInfoDao;
import org.springframework.stereotype.Service;

@Service
public class DrugInfoService extends BaseService<DrugInfoDao, DrugInfo> {

}
