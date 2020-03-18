package com.cybavo.sofa.mock.repository;

import com.cybavo.sofa.mock.entity.ApiToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiTokenRepository extends CrudRepository<ApiToken, Long> {
}
