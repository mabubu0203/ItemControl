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
@ApiModel("GoodsStatusTbl")
@Entity(name = "GoodsStatusTbl")
@Table(
        name = "GOODS_STATUS_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = "GOODS_ID")
)
@Where(clause = "DELETE_FLAG = false")
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsStatusTbl
        extends AbstractStatusEntity
        implements Serializable {

    private static final long serialVersionUID = -5669762434247119049L;

    @Id
    @Column(
            name = "GOODS_ID",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private Long goodsId;

    @OneToOne(
            mappedBy = "statusTbl",
            targetEntity = GoodsTbl.class
    )
    @PrimaryKeyJoinColumn
    private GoodsTbl masterTbl;

}
