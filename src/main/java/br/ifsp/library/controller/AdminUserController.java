package br.ifsp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifsp.library.dto.UserRequestDTO;
import br.ifsp.library.dto.authentication.UserResponseDTO;
import br.ifsp.library.service.UserService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {
	
	@Autowired
	private UserService userService;
	@GetMapping("/all")
	public ResponseEntity<List<UserResponseDTO>> getUsers(){
		return ResponseEntity.ok(userService.getAll());
	}
	
	  @DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    userService.deleteUser(id);
	    return ResponseEntity.noContent().build();
	  }
	  
	  @PatchMapping("/{id}")
	  public ResponseEntity<UserResponseDTO> patchUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
	    return ResponseEntity.ok(userService.updateUser(id, dto));
	  }
	  
	  @PutMapping("/{id}")
	  public ResponseEntity<UserResponseDTO> updateTask(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
	    return ResponseEntity.ok(userService.updateUser(id, dto));
	  }
	  
	  @PatchMapping("/elevate/{id}")
	  public ResponseEntity<UserResponseDTO> elevateUser(@PathVariable Long id) {
	    return ResponseEntity.ok(userService.elevateUser(id));
	  }
}
