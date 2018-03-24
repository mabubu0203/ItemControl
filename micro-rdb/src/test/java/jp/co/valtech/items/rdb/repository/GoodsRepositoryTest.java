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

import javax.persistence.NoResultException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

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
    @DisplayName("GoodsCode検索")
    class findByCodeTest {

        @FlywayTest
        @Test
        void findSuccess() throws Exception {

            String code = "CODEG1";
            Optional<GoodsTbl> opt = repository.findByCode(code);
            GoodsTbl entity = opt.get();

            Assertions.assertEquals(entity.getCode(), code);
            Assertions.assertEquals(entity.getCategoryId(), Long.valueOf("1"));
            Assertions.assertEquals(entity.getName(), "GET");
            Assertions.assertEquals(entity.getPrice(), Integer.valueOf("1"));
            Assertions.assertEquals(entity.getNote(), "NOTE");

        }

        @FlywayTest
        @Test
        void findFail() throws Exception {

            String code = "CODEG100";
            Optional<GoodsTbl> opt = repository.findByCode(code);
            Assertions.assertThrows(NoSuchElementException.class, () -> opt.get());

        }

    }

    @Nested
    @DisplayName("GoodsId検索")
    class findByIdJoinStatusTest {

        @FlywayTest
        @Test
        void findSuccess() throws Exception {

            Long id = Long.valueOf("1");
            GoodsTbl entity = repository.findByIdJoinStatus(id);

            Assertions.assertEquals(entity.getCode(), "CODEG1");
            Assertions.assertEquals(entity.getCategoryId(), Long.valueOf("1"));
            Assertions.assertEquals(entity.getName(), "GET");
            Assertions.assertEquals(entity.getPrice(), Integer.valueOf("1"));
            Assertions.assertEquals(entity.getNote(), "NOTE");

        }

        @FlywayTest
        @Test
        void findFail() throws Exception {

            Long id = Long.valueOf("100");
            Assertions.assertThrows(NoResultException.class, () -> repository.findByIdJoinStatus(id));

        }

    }

    @Nested
    @DisplayName("全検索")
    class getAllJoinStatusTest {

        @FlywayTest
        @Test
        void findSuccess() throws Exception {

            Stream<GoodsTbl> stream = repository.getAllJoinStatus();
            Assertions.assertEquals(stream.count(), 5);

        }

    }

    @Nested
    @DisplayName("条件検索")
    class searchJoinStatusTest {

    }

}
