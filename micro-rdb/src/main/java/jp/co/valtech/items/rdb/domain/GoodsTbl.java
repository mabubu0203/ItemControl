package jp.co.valtech.items.rdb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

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
public class GoodsTbl implements Serializable {

    /** */
    private static final long serialVersionUID = -5665962434247119049L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "ID",
            unique = true,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private long id;

    @Column(
            name = "CODE",
            unique = true,
            length = 10,
            columnDefinition = "VARCHAR"
    )
    private String code;

    @Column(
            name = "NAME",
            length = 25,
            columnDefinition = "VARCHAR"
    )
    private String name;

    @Column(
            name = "PRICE",
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
