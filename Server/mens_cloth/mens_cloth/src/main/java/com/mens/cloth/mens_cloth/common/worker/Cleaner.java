package com.mens.cloth.mens_cloth.common.worker;

import com.mens.cloth.mens_cloth.auth.repository.TokenRepository;
import com.mens.cloth.mens_cloth.auth.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Configuration
public class Cleaner {

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(cron = "0 0/10 * * * ?")
    @Transactional
    public void cleanCode(){
        LocalDateTime now = LocalDateTime.now();
        verificationCodeRepository.deleteByExpiredAtLessThan(now);
        tokenRepository.deleteByExpiredAtLessThan(now);
        System.out.println("Cleaned");
    }
}
