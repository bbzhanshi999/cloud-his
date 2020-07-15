package edu.ncu.doctor.service;

import edu.ncu.commons.model.MedicalRecord;
import edu.ncu.commons.service.BaseService;
import edu.ncu.doctor.dao.MrDao;
import edu.ncu.doctor.pojo.MrDrugInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrService extends BaseService<MrDao, MedicalRecord> {

    /**
     * 更新mr表中的payStatus字段为1
     * 在mr_drug表中添加多条数据
     * @param mrDrugInfo
     */
    @Transactional
    public void createMrDrugInfo(MrDrugInfo mrDrugInfo) {
        MedicalRecord record = new MedicalRecord();
        record.setId(mrDrugInfo.getMrId());
        record.setPayStatus("1");
        record.setAmount(mrDrugInfo.getAmount());
        this.dao.update(record);
        //插入多条mrDrugs数据
        //给每一个mrDrug对象附上mrId
        mrDrugInfo.getMrDrugs().forEach(mrDrug -> mrDrug.setMrId(mrDrugInfo.getMrId()));
        this.dao.insertManyMrDrug(mrDrugInfo.getMrDrugs());

    }
}
