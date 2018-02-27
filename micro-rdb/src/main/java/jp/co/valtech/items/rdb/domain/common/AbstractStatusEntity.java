package jp.co.valtech.items.rdb.domain.common;

import jp.co.valtech.items.rdb.domain.common.interfaces.StatusEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@MappedSuperclass
public class AbstractStatusEntity
        implements StatusEntity {

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

    /**
     * @author uratamanabu
     * @since 1.0
     */
    @PrePersist
    @Override
    public void onPrePersist() {
        version = 0;
        deleteFlag = false;
        createDatetime = LocalDateTime.now();
        updateDatetime = LocalDateTime.now();
    }

    /**
     * @author uratamanabu
     * @since 1.0
     */
    @PreUpdate
    @Override
    public void onPreUpdate() {
        updateDatetime = LocalDateTime.now();
    }

    /**
     * @author uratamanabu
     * @since 1.0
     */
    @Override
    public void addVersion() {
        version++;
    }

}
