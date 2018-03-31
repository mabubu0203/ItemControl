package jp.co.valtech.items.rdb.domain;

import io.swagger.annotations.ApiModel;
import jp.co.valtech.items.rdb.domain.common.AbstractMasterEntity;
import jp.co.valtech.items.validator.constraints.CategoryCodeField;
import jp.co.valtech.items.validator.constraints.CategoryNameField;
import jp.co.valtech.items.validator.constraints.CategoryNoteField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.List;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@ApiModel("CategoryTbl")
@Entity(name = "CategoryTbl")
@Table(
        name = "CATEGORY_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "CODE"})
)
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryTbl
        extends AbstractMasterEntity
        implements Serializable {

    private static final long serialVersionUID = -5665962434747119049L;

    @Column(
            name = "CODE",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "VARCHAR"
    )
    @CategoryCodeField
    private String code;

    @Column(
            name = "NAME",
            nullable = false,
            length = 25,
            columnDefinition = "VARCHAR"
    )
    @CategoryNameField
    private String name;

    @Column(
            name = "NOTE",
            length = 64,
            columnDefinition = "VARCHAR"
    )
    @CategoryNoteField
    private String note;

    @OneToMany(mappedBy = "categoryTbl")
    private List<GoodsTbl> goodsTblList;

    @OneToOne(targetEntity = CategoryStatusTbl.class)
    @PrimaryKeyJoinColumn
    private CategoryStatusTbl statusTbl;

}
