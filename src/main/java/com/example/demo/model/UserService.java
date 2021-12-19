package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        final Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()){
            throw new UserNotFoundException("Could not find any users with id :"+id);
        }
        return result.get();
    }

    public void delete(Integer id) throws UserNotFoundException {
        final Long count = userRepository.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with id :"+id);
        }
        userRepository.deleteById(id);
    }
}
