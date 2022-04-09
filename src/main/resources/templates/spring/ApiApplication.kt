package #{PackageName}

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableCaching
@EnableJdbcRepositories
@SpringBootApplication
@EnableJpaAuditing
class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
