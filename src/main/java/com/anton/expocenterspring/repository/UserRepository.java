package com.anton.expocenterspring.repository;

import com.anton.expocenterspring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
