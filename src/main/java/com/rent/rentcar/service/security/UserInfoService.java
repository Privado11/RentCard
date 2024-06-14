package com.rent.rentcar.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rent.rentcar.dto.security.UserInfo;
import com.rent.rentcar.models.User;
import com.rent.rentcar.repository.UserRepository;

@Service
public class UserInfoService implements UserDetailsService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByEmail(email);
        return userDetail.map(UserInfoDetail::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public UserInfo addUser(UserInfo userInfo) {
        User user = new User(null, userInfo.name(), userInfo.lastName(), userInfo.idCard(), userInfo.email(), userInfo.address(), userInfo.phone(), passwordEncoder.encode(userInfo.password()), userInfo.role() );
        user = userRepository.save(user);
        return new UserInfo(user.getName(), user.getLastName(), user.getIdCard(), user.getEmail(), user.getAddress(), user.getPhone(), userInfo.password(), user.getRoles());

    }

}
