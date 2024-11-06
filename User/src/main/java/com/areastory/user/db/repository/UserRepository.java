package com.areastory.user.db.repository;

import com.areastory.user.db.entity.UserInfo;
import com.areastory.user.db.repository.support.UserRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long>, UserRepositorySupport {
    Optional<UserInfo> findByProviderId(Long providerId);

}
