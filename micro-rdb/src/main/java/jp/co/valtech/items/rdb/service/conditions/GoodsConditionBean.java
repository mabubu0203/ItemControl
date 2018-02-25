package jp.co.valtech.items.rdb.service.conditions;

import lombok.Data;

@Data
public class GoodsConditionBean {

    private String code;

    private String name;

    private Integer price_from;

    private Integer price_to;

    private String note;

}
