package jp.co.valtech.items.rdb.domain.common.interfaces;

import java.time.LocalDateTime;

public interface StatusEntity {
    Integer version = null;
    Boolean deleteFlag = null;
    LocalDateTime createDatetime = LocalDateTime.now();
    LocalDateTime updateDatetime = LocalDateTime.now();

    void onPrePersist();

    void onPreUpdate();

    void addVersion();
}
