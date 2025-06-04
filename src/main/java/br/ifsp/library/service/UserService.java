package br.ifsp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ifsp.library.dto.UserRequestDTO;
import br.ifsp.library.dto.authentication.UserRegistrationDTO;
import br.ifsp.library.dto.authentication.UserResponseDTO;
import br.ifsp.library.exception.ResourceNotFoundException;
import br.ifsp.library.model.RoleType;
import br.ifsp.library.model.User;
import br.ifsp.library.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserResponseDTO createUser(UserRegistrationDTO userDto) {
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		User user = new User(userDto.getName(), userDto.getEmail(), encodedPassword, RoleType.DEFAULT);
		userRepository.save(user);
		return user.transformDto(user);
	}

	public List<UserResponseDTO> getAll() {
		return User.transformListDto(userRepository.findAll());
	}

	public boolean deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
		userRepository.delete(user);
		return true;
	}

	public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
		User updated = userRepository.save(user);
		return user.transformDto(updated);
	}
	
	public UserResponseDTO elevateUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
		user.setRole(RoleType.ADMIN);
		return user.transformDto(userRepository.save(user));
	}
}
