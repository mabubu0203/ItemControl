package jp.co.valtech.items.rdb.repository;

import jp.co.valtech.items.rdb.domain.GoodsTbl;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
@FlywayTest(locationsForMigrate = {"/db/migration/h2"})
@DisplayName("商品マスタ")
class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository repository;

    @Nested
    @DisplayName("登録")
    class findByCodeTest {

        @FlywayTest
        @Test
        void find1() throws Exception {

            String code = "CODEG1";
            Optional<GoodsTbl> opt = repository.findByCode(code);
            GoodsTbl entity = opt.get();
            Assertions.assertEquals(entity.getCode(), code);
            Assertions.assertEquals(entity.getName(), "GET");

        }


    }


}
