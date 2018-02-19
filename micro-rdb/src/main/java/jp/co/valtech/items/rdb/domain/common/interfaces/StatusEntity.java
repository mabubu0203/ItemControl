package jp.co.valtech.items.rdb.domain.common.interfaces;

import java.time.LocalDateTime;

public interface StatusEntity {
    int version = 0;
    boolean deleteFlag = false;
    LocalDateTime createDatetime = LocalDateTime.now();
    LocalDateTime updateDatetime = LocalDateTime.now();

    void onPrePersist();

    void onPreUpdate();

    void addVersion();
}
