package com.bct.weeklystatus.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.Role;
import com.bct.weeklystatus.entities.RoleName;
import com.bct.weeklystatus.entities.User;
import com.bct.weeklystatus.model.JwtResponse;
import com.bct.weeklystatus.model.LoginForm;
import com.bct.weeklystatus.model.ResponseMessage;
import com.bct.weeklystatus.model.SignUpForm;
import com.bct.weeklystatus.repositories.RoleRepository;
import com.bct.weeklystatus.repositories.UserRepository;
import com.bct.weeklystatus.security.jwt.JwtProvider;
import com.bct.weeklystatus.services.ProjectDetailService;
import com.bct.weeklystatus.services.UserDetailsServiceImpl;
import com.bct.weeklystatus.services.UserPrinciple;
@RestController
@RequestMapping("/bctuser")
//@CrossOrigin(origins = {"http://localhost:4200","http://10.240.8.62:7001","http://localhost:8080","http://localhost:8088"}, allowedHeaders="*")

public class UserController {
	@Autowired
	  AuthenticationManager authenticationManager;
	 
	  @Autowired
	  UserRepository userRepository;
	 
	  @Autowired 
	  UserDetailsServiceImpl userService;
	  @Autowired 
	  ProjectDetailService projectDetailService;
	  @Autowired
	  RoleRepository roleRepository;
	 
	  @Autowired
	  PasswordEncoder encoder;
	 
	  @Autowired
	  JwtProvider jwtProvider;
	 
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
	 
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	 
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	    String jwt = jwtProvider.generateJwtToken(authentication);
	    UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
	 
	    return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getName(),userDetails.getUsername(), userDetails.getAuthorities()));
	  }
	 
	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
	          HttpStatus.BAD_REQUEST);
	    }
	 
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
	          HttpStatus.BAD_REQUEST);
	    }
	 
	    // Creating user's account
	    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
	        encoder.encode(signUpRequest.getPassword()));
	 
	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();
	 
	    strRoles.forEach(role -> {
	      switch (role) {
	      case "admin":
	        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
	            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        roles.add(adminRole);
	 
	        break;
	      case "pm":
	        Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
	            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        roles.add(pmRole);
	 
	        break;
	      default:
	        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        roles.add(userRole);
	      }
	    });
	 
	    user.setRoles(roles);
	    userRepository.save(user);
	 
	    return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	  }
	
	
    @DeleteMapping(path="/user/delete/{userid}" ,consumes="application/json", produces="application/json")
	public ResponseEntity deleteBctLogUser(@PathVariable String userid)
	{
    	System.out.println("deleteBctLogUser "+userid);
    	userService.deleteUser(userid);
    	return new ResponseEntity<>("User with ID :" + userid + " deleted successfully", HttpStatus.OK);
    
	}
    
     @GetMapping("/projectlist")
	public List<com.bct.weeklystatus.model.ProjectDetail> getProjectDetails()
	{
    	System.out.println("getProjectDetails ...");
    	List<ProjectDetail> projectDetailsList = projectDetailService.getAllProjectDetails();
    	
	List<com.bct.weeklystatus.model.ProjectDetail> projectDetailsListNew = new ArrayList<com.bct.weeklystatus.model.ProjectDetail>();
        
    	projectDetailsList.forEach(projectDetail ->{
     	      com.bct.weeklystatus.model.ProjectDetail projectDetailTemp = new com.bct.weeklystatus.model.ProjectDetail();
     	       projectDetailTemp.setProjectId(projectDetail.getProjectId());
     	      projectDetailTemp.setProjectName(projectDetail.getProjectName());
         	  projectDetailTemp.setWeekDuration(projectDetail.getWeekDuration());
     	      projectDetailsListNew.add(projectDetailTemp);

     	   
     	          });
        System.out.println("return ...");
    	
    	return projectDetailsListNew;
    	}
	
    @GetMapping("/all/bctlogusers")
	public List<User> getAllUsers()
	{
    	System.out.println("getAllUsers ...");
    	return userService.findAllUsers();
    			
  }
	
	
		
	
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}