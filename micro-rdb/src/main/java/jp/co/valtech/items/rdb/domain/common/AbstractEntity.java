package jp.co.valtech.items.rdb.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "ID",
            unique = true,
            nullable = false,
            length = 10,
            columnDefinition = "BIGINT"
    )
    private long id;

    @Version
    @Column(
            name = "VERSION",
            nullable = false,
            length = 5,
            columnDefinition = "INT"
    )
    private int version;

    @Column(
            name = "CREATE_DATETIME",
            nullable = false,
            columnDefinition = "DATETIME"
    )
    private LocalDateTime createDatetime;

    @Column(
            name = "UPDATE_DATETIME",
            nullable = false,
            columnDefinition = "DATETIME"
    )
    private LocalDateTime updateDatetime;

    @PrePersist
    public void onPrePersist() {
        setCreateDatetime(LocalDateTime.now());
        setUpdateDatetime(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        addVersion();
        setUpdateDatetime(LocalDateTime.now());
    }

    private void addVersion() {
        int count = getVersion();
        setVersion(count++);
    }
}
