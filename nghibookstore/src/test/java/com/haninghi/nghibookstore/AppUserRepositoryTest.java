package com.haninghi.nghibookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.haninghi.nghibookstore.domain.AppUser;
import com.haninghi.nghibookstore.domain.AppUserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void findByUsernameShouldReturnUsername() {
        AppUser user = appUserRepository.findByUsername("user");
        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getRole()).isEqualTo("USER");
    }

    @Test
    public void createNewUser() {
        AppUser user = new AppUser("admin1", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"ADMIN");
        appUserRepository.save(user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("admin1");
    }
}
