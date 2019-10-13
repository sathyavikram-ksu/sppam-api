package com.group1.sppam.repository;

import com.group1.sppam.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
