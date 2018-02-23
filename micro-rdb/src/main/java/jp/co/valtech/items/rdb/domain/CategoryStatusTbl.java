package jp.co.valtech.items.rdb.domain;

import jp.co.valtech.items.rdb.domain.common.AbstractStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity(name = "CategoryStatusTbl")
@Table(
        name = "CATEGORY_STATUS_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = "CATEGORY_ID")
)
@Where(clause = "DELETE_FLAG = false")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryStatusTbl
        extends AbstractStatusEntity implements Serializable {

    /** */
    private static final long serialVersionUID = -5669762434897119049L;

    @Id
    @Column(
            name = "CATEGORY_ID",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private long categoryId;

    @OneToOne(
            mappedBy = "statusTbl",
            targetEntity = CategoryTbl.class
    )
    @PrimaryKeyJoinColumn
    private CategoryTbl masterTbl;

}