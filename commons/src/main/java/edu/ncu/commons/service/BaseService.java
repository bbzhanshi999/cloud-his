package edu.ncu.commons.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.ncu.commons.dao.BaseDao;
import edu.ncu.commons.model.BaseModel;
import edu.ncu.commons.pojo.PageCondition;
import edu.ncu.commons.utils.IdGen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class BaseService<D extends BaseDao<M>,M extends BaseModel> {

    @Autowired
    protected D dao;

    protected IdGen idGen = ()->UUID.randomUUID().toString().replaceAll("-","");

    public void insertMany(List<M> list){
        this.insertMany(list,idGen);
    }

    public void insertMany(List<M> list,IdGen idGen){
        list.forEach(m->m.preInsert(idGen));
        dao.insertMany(list);
    }

    public void delete(M condition){
        dao.delete(condition);
    }

    public void deleteById(String id){
        dao.deleteById(id);
    }

    public M find(M condition){
        return dao.find(condition);
    }

    public M findById(String id){
        return dao.findById(id);
    }

    public List<M> findList(M condition){
        return dao.findList(condition);
    }
    public List<M> findAll(){
        return dao.findAll();
    }

    public void save(M m, IdGen idGen){
        if(m.getId()==null ||"".equals(m.getId())){
            //插入
            m.preInsert(idGen);
            dao.insert(m);
        }else{
            //更新
            m.preUpdate();
            dao.update(m);
        }
    }

    /**
     * 插入或更新数据
     */
    public void save(M m){
        this.save(m,idGen);
    }

    /**
     * 分页获取数据
     * @param pc
     * @return
     */
    public PageInfo<M> page(PageCondition<M> pc){
        return PageHelper
                .startPage(pc.getPageNum(),pc.getPageSize())
                .doSelectPageInfo(()->dao.findList(pc.getCondition()));
    }

}
