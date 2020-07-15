package edu.ncu.doctor.dao;

import edu.ncu.commons.dao.BaseDao;
import edu.ncu.commons.model.MedicalRecord;
import edu.ncu.doctor.pojo.MrDrug;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MrDao extends BaseDao<MedicalRecord> {

    void insertManyMrDrug(List<MrDrug> mrDrugs);
}
