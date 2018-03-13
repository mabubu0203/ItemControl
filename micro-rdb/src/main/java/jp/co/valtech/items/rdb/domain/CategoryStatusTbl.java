package jp.co.valtech.items.rdb.domain;

import io.swagger.annotations.ApiModel;
import jp.co.valtech.items.rdb.domain.common.AbstractStatusEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@ApiModel("CategoryStatusTbl")
@Entity(name = "CategoryStatusTbl")
@Table(
        name = "CATEGORY_STATUS_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = "CATEGORY_ID")
)
@Where(clause = "DELETE_FLAG = false")
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryStatusTbl
        extends AbstractStatusEntity
        implements Serializable {

    private static final long serialVersionUID = -5669762434897119049L;

    @Id
    @Column(
            name = "CATEGORY_ID",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private Long categoryId;

    @OneToOne(
            mappedBy = "statusTbl",
            targetEntity = CategoryTbl.class
    )
    @PrimaryKeyJoinColumn
    private CategoryTbl masterTbl;

}
