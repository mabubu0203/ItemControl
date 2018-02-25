package jp.co.valtech.items.rdb.service.conditions;

import lombok.Data;

@Data
public class GoodsConditionBean {

    private String code;

    private String name;

    private int price_from;

    private int price_to;

    private String note;

}
