package com.mens.cloth.mens_cloth.auth.repository;

import com.mens.cloth.mens_cloth.auth.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {


    Optional<VerificationCode> findByIdAndCode(Long id, String otp);



    void deleteByExpiredAtLessThan(LocalDateTime now);
}
