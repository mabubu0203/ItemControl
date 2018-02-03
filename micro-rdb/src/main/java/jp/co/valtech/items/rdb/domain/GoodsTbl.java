package jp.co.valtech.items.rdb.domain;

import jp.co.valtech.items.rdb.domain.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Entity
@Data
@ToString
@Table(
        name = "GOODS_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = "CODE")
)
@NoArgsConstructor
@AllArgsConstructor
public class GoodsTbl extends AbstractEntity implements Serializable {

    /** */
    private static final long serialVersionUID = -5665962434247119049L;

    @Column(
            name = "CODE",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR"
    )
    private String code;

    @Column(
            name = "NAME",
            nullable = false,
            length = 25,
            columnDefinition = "VARCHAR"
    )
    private String name;

    @Column(
            name = "PRICE",
            nullable = false,
            length = 4,
            columnDefinition = "SMALLINT"
    )
    private int price;

    @Column(
            name = "NOTE",
            length = 64,
            columnDefinition = "VARCHAR"
    )
    private String note;

}
