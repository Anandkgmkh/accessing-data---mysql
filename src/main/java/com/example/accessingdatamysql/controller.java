package com.example.accessingdatamysql;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@Validated
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class controller {
	
  @Autowired 
  private UserRepository userRepository;
  
  @Autowired
  JdbcTemplate jdbcTemplate;

 
	
	/*
	 * @PostMapping(path="/add") public String
	 * addNewUser(@Valid @RequestBody @RequestParam("name") String name
	 * , @RequestParam("email") String email) {
	 * 
	 * User n = new User(); n.setName(name); n.setEmail(email);
	 * userRepository.save(n); return "Saved"; }
	 */
	 
	
	  @PostMapping(path="/add")
	  public String addNewUser ( @RequestBody User user) {
	  
	  userRepository.save(user);
	  return "Saved"; 
	  }
	 
  
  @PostMapping(path="/addMore") // Map ONLY POST Requests
  public String addNewMoreUser (@RequestBody Iterable<User> u) {
  
    userRepository.saveAll(u);
    return "SavedAll";
  }

  @GetMapping("/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
  
  
  
  @GetMapping(path="/count")
  public int count()
  {
	 return (int) userRepository.count();
  }
  
  @GetMapping(path="/count1")
  public int count1()
  {    //custom method
	  //String sql="select count(*) from User";
	   //jdbcTemplate.update(sql);
	  int n=userRepository.indByCount();
	  return n;
  }
 
  @GetMapping("/findemail/{name}")
  public User returnemail1(@PathVariable("name") String s)
  {
	  return userRepository.findByName(s);
	  
  }
  
 
  
  @GetMapping("/findemail1/{name}")
  public String returnemail(@PathVariable("name") String s)
  {
	  String email=userRepository.findByEmail1(s);
	  return email;
  }
  
  @DeleteMapping("/delete/{id}")
  public String deleteRow(@PathVariable("id") int id)
  {
	  userRepository.deleteById(id);
	  return "row deleted...";
  }
 
  @DeleteMapping("/deleteall")
  public String deleteall()
  {
	  userRepository.deleteAll();
	  return "AllDataDeleted";
  }
  
}
