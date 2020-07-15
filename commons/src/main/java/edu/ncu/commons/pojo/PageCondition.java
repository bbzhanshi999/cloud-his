package edu.ncu.commons.pojo;

import edu.ncu.commons.model.BaseModel;
import lombok.Data;


@Data
public class PageCondition<M extends BaseModel> {

    private M condition;
    private Integer pageNum;
    private Integer pageSize;
}
