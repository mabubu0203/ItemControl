package jp.co.valtech.items.rdb.domain.common;

import jp.co.valtech.items.rdb.domain.common.interfaces.UniqueEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class AbstractMasterEntity implements UniqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "ID",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private long id;
}
