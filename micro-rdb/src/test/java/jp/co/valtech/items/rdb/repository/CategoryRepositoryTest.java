package jp.co.valtech.items.rdb.repository;

import jp.co.valtech.items.rdb.domain.CategoryTbl;
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

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        FlywayTestExecutionListener.class})
@FlywayTest(locationsForMigrate = {"/db/migration/h2"})
@DisplayName("カテゴリーマスタ")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Nested
    @DisplayName("GoodsCode検索")
    class findByCodeTest {

        @FlywayTest
        @Test
        void findSuccess() throws Exception {

            String code = "CODEC1";
            Optional<CategoryTbl> opt = repository.findByCode(code);
            CategoryTbl entity = opt.get();

            Assertions.assertEquals(entity.getCode(), code);
            Assertions.assertEquals(entity.getName(), "GET");
            Assertions.assertEquals(entity.getNote(), "NOTE");

        }

        @FlywayTest
        @Test
        void findFail() throws Exception {

            String code = "CODEC100";
            Optional<CategoryTbl> opt = repository.findByCode(code);
            Assertions.assertThrows(NoSuchElementException.class, () -> opt.get());

        }

    }

    @Nested
    @DisplayName("全検索")
    class getAllJoinStatusTest {

        @FlywayTest
        @Test
        void findSuccess() throws Exception {

            Stream<CategoryTbl> stream = repository.getAllJoinStatus();
            Assertions.assertEquals(stream.count(), 4);

        }

    }
}
