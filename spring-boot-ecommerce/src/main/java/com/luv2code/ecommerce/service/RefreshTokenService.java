package com.luv2code.ecommerce.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.ecommerce.dao.RefreshTokenRepository;
import com.luv2code.ecommerce.entity.RefreshToken;
import com.luv2code.ecommerce.exceptions.ecommmerceException;

import lombok.AllArgsConstructor;

@Service
@Transactional
public class RefreshTokenService {

	@Autowired
    RefreshTokenRepository refreshTokenRepository  ;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ecommmerceException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
            refreshTokenRepository.deleteByToken(token);
    }
}
