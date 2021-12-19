package com.example.demo.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void save(){
        final User user = new User(1,"kattelk4b1n@gmail.com","mypassword","Kabindra","Kattel");
        userRepository.save(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void getByID(){
        final User user1 = new User(1,"kattel4b1n@gmail.com","mypassword","Kabindra","Kattel");
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));

        final Optional<User> findById = userRepository.findById(1);
        assertFalse(findById.isEmpty());
        final User user = findById.get();
        assertEquals(user.getEmail(),user1.getEmail());
        assertEquals(user.getPassword(),user1.getPassword());
        assertEquals(user.getFirstName(),user1.getFirstName());
        assertEquals(user.getLastName(),user1.getLastName());
    }

}
