package sk.havkymnauky.restaurant.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.model.Role;
import sk.havkymnauky.restaurant.model.User;

@Service
public class UserDBInit implements CommandLineRunner {

    @Autowired
    @Qualifier("JDBCPostgresqlUserRepo")
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (this.userRepository.getAll().size() == 0) {
            this.userRepository.saveUser(new User(0, "Admin", passwordEncoder.encode("admin123"), Role.ADMIN));
        }
    }
}
