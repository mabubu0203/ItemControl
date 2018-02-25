package jp.co.valtech.items.rdb.domain.common;

import jp.co.valtech.items.rdb.domain.common.interfaces.StatusEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
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
public class AbstractStatusEntity implements StatusEntity {

    @Version
    @Column(
            name = "VERSION",
            nullable = false,
            length = 5,
            columnDefinition = "INT"
    )
    private Integer version;

    @Column(
            name = "DELETE_FLAG",
            nullable = false,
            columnDefinition = "TINYINT"
    )
    private Boolean deleteFlag;

    @CreatedDate
    @Column(
            name = "CREATE_DATETIME",
            nullable = false,
            columnDefinition = "DATETIME"
    )
    private LocalDateTime createDatetime;

    @LastModifiedDate
    @Column(
            name = "UPDATE_DATETIME",
            nullable = false,
            columnDefinition = "DATETIME"
    )
    private LocalDateTime updateDatetime;

    @PrePersist
    @Override
    public void onPrePersist() {
        version = 0;
        deleteFlag = false;
        createDatetime = LocalDateTime.now();
        updateDatetime = LocalDateTime.now();
    }

    @PreUpdate
    @Override
    public void onPreUpdate() {
        updateDatetime = LocalDateTime.now();
    }

    @Override
    public void addVersion() {
        version++;
    }

}
