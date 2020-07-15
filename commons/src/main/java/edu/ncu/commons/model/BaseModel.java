package edu.ncu.commons.model;

import edu.ncu.commons.utils.IdGen;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel implements Serializable{
    //要想使用redis默认的java序列化规则，必须要实现Serializable接口

    public static final String NORMAL_DATA="0";
    public static final String DELETE_DATA="1";

    protected String id;
    protected String delFlag;
    protected Date updateTime;

    /**
     * 插入之前完成的动作：添加ID，创建更新时间
     */
    public void preInsert(IdGen idGen){
        this.id = idGen.genId();
        this.updateTime = new Date();
    }

    /**
     * 更新一下时间
     */
    public void preUpdate(){
        this.updateTime = new Date();
    }

}
