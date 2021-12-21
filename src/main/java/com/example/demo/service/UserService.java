package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void save(User user);

    User get(Integer id) throws UserNotFoundException;

    void delete(Integer id) throws UserNotFoundException;

    Page<User> getAllPaginated(int pageNo, int offsetSize);

}
