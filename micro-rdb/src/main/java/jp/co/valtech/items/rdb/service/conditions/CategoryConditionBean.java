package jp.co.valtech.items.rdb.service.conditions;

import lombok.Data;

import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
public class CategoryConditionBean
        implements Serializable {

    private static final long serialVersionUID = 1349050140871443209L;

    private String code;

    private String name;

    private String note;
}
