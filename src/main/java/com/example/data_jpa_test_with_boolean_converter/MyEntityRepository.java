package com.example.data_jpa_test_with_boolean_converter;

import org.springframework.data.repository.CrudRepository;

public interface MyEntityRepository extends CrudRepository<MyEntity, Long> {
}
