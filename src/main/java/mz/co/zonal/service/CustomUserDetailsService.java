package mz.co.zonal.service;

import mz.co.zonal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        var user = repository.findByEmail(s);

        if (user == null){
            throw new UsernameNotFoundException("Usuário nao encontrado!");
        }
        return new User(user.getFullName(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
}
