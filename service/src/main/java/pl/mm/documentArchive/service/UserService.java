package pl.mm.documentArchive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.daoRepository.UserRepository;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service(UserService.BEAN_NAME)
public class UserService extends BaseDocumentArchiverService<User> implements UserDetailsService {

	@SuppressWarnings("WeakerAccess")
	public static final String BEAN_NAME = "userService";

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository) {
		repository = userRepository;
	}

	@Transactional
	public void addUser(User user) {
		user = setDefaultVariables(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
	}

	@Transactional
	public void removeUser(User user) {
		User foundUser = findByUserName(user.getUserName());
		repository.delete(foundUser);
	}

	@SuppressWarnings("WeakerAccess")
	@Transactional
	public User findByUserName(String userName) {
		return ((UserRepository) repository).findByUserName(userName).orElse(null);
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = ((UserRepository) repository).findByUserName(userName).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}
}
