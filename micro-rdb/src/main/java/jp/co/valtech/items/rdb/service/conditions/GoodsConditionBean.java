package jp.co.valtech.items.rdb.service.conditions;

import lombok.Data;

import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
public class GoodsConditionBean
        implements Serializable {

    private static final long serialVersionUID = 1349750140871443209L;

    private String code;

    private String name;

    private Integer price_from;

    private Integer price_to;

    private String note;

}
