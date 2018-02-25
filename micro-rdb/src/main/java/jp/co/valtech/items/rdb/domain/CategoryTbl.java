package jp.co.valtech.items.rdb.domain;

import jp.co.valtech.items.interfaces.validator.constraint.CategoryCodeField;
import jp.co.valtech.items.interfaces.validator.constraint.CategoryNameField;
import jp.co.valtech.items.interfaces.validator.constraint.CategoryNoteField;
import jp.co.valtech.items.rdb.domain.common.AbstractMasterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @OneToOne(targetEntity = CategoryStatusTbl.class)
    @PrimaryKeyJoinColumn
    private CategoryStatusTbl statusTbl;

}
