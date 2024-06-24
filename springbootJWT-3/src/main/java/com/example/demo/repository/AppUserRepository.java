//package com.example.demo.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.example.demo.model.AppUser;
//
//public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
//public AppUser findByUsername(String username);
//public AppUser findByEmail(String email);
//
//	
//}
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
}
