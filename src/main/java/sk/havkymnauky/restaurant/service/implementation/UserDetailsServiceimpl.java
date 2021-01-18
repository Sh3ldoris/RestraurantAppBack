package sk.havkymnauky.restaurant.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.model.User;
import sk.havkymnauky.restaurant.repository.IUserRepository;
import sk.havkymnauky.restaurant.security.UserPrincipal;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    @Qualifier("JDBCPostgresqlUserRepo")
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user  = userRepository.getByUsername(s);
        UserPrincipal principal = new UserPrincipal(user);
        return principal;
    }
}
