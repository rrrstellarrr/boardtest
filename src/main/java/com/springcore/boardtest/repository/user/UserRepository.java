package com.springcore.boardtest.repository.user;

import com.springcore.boardtest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

//    Optional<User> findById(Long id);

//    @Query("select u from User u where u.userIdx = :userIdx and u.username = :username")
//    Optional<User> findByUserInfo(@Param("userIdx") Long id, @Param("username") String username, @Param("username") String userName);
//
//
//    @Modifying
//    @Query("update User u set u.password = :password, u.email = :email, u.nickname = :nickname")
//    int editUser(@Param("password") String password, @Param("email") String email, @Param("nickname") String nickname);
}
