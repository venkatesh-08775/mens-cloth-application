package com.mens.cloth.mens_cloth.auth.repository;

import com.mens.cloth.mens_cloth.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
}
