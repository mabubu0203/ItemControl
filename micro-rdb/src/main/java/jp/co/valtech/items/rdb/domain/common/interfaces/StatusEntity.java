package jp.co.valtech.items.rdb.domain.common.interfaces;

import java.time.LocalDateTime;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
public interface StatusEntity {
    Integer version = null;
    Boolean deleteFlag = null;
    LocalDateTime createDatetime = LocalDateTime.now();
    LocalDateTime updateDatetime = LocalDateTime.now();

    /**
     * @author uratamanabu
     * @since 1.0
     */
    void onPrePersist();

    /**
     * @author uratamanabu
     * @since 1.0
     */
    void onPreUpdate();

    /**
     * @author uratamanabu
     * @since 1.0
     */
    void addVersion();
}
