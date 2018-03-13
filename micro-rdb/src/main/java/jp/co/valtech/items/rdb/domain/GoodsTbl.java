package jp.co.valtech.items.rdb.domain;

import io.swagger.annotations.ApiModel;
import jp.co.valtech.items.interfaces.validator.constraint.GoodsCodeField;
import jp.co.valtech.items.interfaces.validator.constraint.GoodsNameField;
import jp.co.valtech.items.interfaces.validator.constraint.GoodsNoteField;
import jp.co.valtech.items.interfaces.validator.constraint.PriceField;
import jp.co.valtech.items.rdb.domain.common.AbstractMasterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@ApiModel("GoodsTbl")
@Entity(name = "GoodsTbl")
@Table(
        name = "GOODS_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "CODE"})
)
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsTbl
        extends AbstractMasterEntity
        implements Serializable {

    private static final long serialVersionUID = -5665962434247119049L;

    @Column(
            name = "CODE",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR"
    )
    @GoodsCodeField
    private String code;

    @Column(
            name = "CATEGORY_ID",
            nullable = false,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private Long categoryId;

    @Column(
            name = "NAME",
            nullable = false,
            length = 25,
            columnDefinition = "VARCHAR"
    )
    @GoodsNameField
    private String name;

    @Column(
            name = "PRICE",
            nullable = false,
            length = 4,
            columnDefinition = "SMALLINT"
    )
    @PriceField
    private Integer price;

    @Column(
            name = "NOTE",
            length = 64,
            columnDefinition = "VARCHAR"
    )
    @GoodsNoteField
    private String note;

    @ManyToOne(targetEntity = CategoryTbl.class)
    @JoinColumn(
            name = "category_id",
            insertable = false,
            updatable = false
    )
    private CategoryTbl categoryTbl;

    @OneToOne(targetEntity = GoodsStatusTbl.class)
    @PrimaryKeyJoinColumn
    private GoodsStatusTbl statusTbl;

}
