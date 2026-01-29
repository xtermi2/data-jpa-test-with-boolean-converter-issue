package com.example.data_jpa_test_with_boolean_converter;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
//import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
// only for testing with spring-boot 3.5
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = true, properties = {
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true",
        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.orm.jdbc.bind=TRACE",
})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MyEntityRepositoryTest {

    @Autowired
    private MyEntityRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testWithConverter() {
        MyEntity entity = new MyEntity();
        entity.setName("Test Entity");
        entity.setPaused(true);

        MyEntity savedEntity = repository.save(entity);

        flushAndClear();

        assertThat(savedEntity.getPaused())
                .isTrue();

        savedEntity.setPaused(null);

        repository.save(savedEntity);

        flushAndClear();

        MyEntity fetchedEntity = repository.findById(savedEntity.getId()).orElseThrow();

        assertThat(fetchedEntity.getPaused())
                .isNull();
    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}