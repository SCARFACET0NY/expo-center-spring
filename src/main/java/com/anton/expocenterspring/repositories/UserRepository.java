package com.anton.expocenterspring.repositories;

import com.anton.expocenterspring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
