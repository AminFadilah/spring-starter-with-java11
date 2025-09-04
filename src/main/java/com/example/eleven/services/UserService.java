package com.example.eleven.services;

import com.example.eleven.dto.LoginRequest;
import com.example.eleven.dto.LoginResponse;
import com.example.eleven.dto.RegisterRequest;
import com.example.eleven.dto.UserDto;
import com.example.eleven.models.Person;
import com.example.eleven.repositories.PersonRepository;
import com.example.eleven.security.JwtUtil;
import com.example.eleven.helper.exception.InvalidCredentialsException;
import com.example.eleven.models.User;
import com.example.eleven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    final JwtUtil jwtUtil;

    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username has been used!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email has been used!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Person person = new Person();
        user.setPerson(person);
        person.setUser(user);
        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved, UserDto.class);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
         return new LoginResponse(jwtUtil.generateToken(loginRequest.getUsername()));
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
