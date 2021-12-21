package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User get(Integer id) throws UserNotFoundException {
        final Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()){
            throw new UserNotFoundException("Could not find any users with id :"+id);
        }
        return result.get();
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        final Long count = userRepository.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with id :"+id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getAllPaginated(int pageNo, int offsetSize) {
        return userRepository.findAll(PageRequest.of(pageNo-1, offsetSize));
    }
}
