package jp.co.valtech.items.rdb;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Configuration
@ComponentScan
@EntityScan(basePackages = {"jp.co.valtech.items.rdb.domain"})
@EnableJpaRepositories(basePackages = {"jp.co.valtech.items.rdb.repository"})
@EnableTransactionManagement(proxyTargetClass = true)
public class RdbCore {
}
