////package com.example.demo.controller;
////
////import java.time.Instant;
////import java.util.Date;
////import java.util.HashMap;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
////import org.springframework.security.oauth2.jwt.JwsHeader;
////import org.springframework.security.oauth2.jwt.JwtClaimsSet;
////import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
////import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
////import org.springframework.validation.BindingResult;
////import org.springframework.validation.FieldError;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import com.example.demo.model.AppUser;
////import com.example.demo.model.RegisterDto;
////import com.example.demo.repository.AppUserRepository;
////import com.nimbusds.jose.jwk.source.ImmutableSecret;
////
////@RestController
////@RequestMapping("/account")
////public class AccountController {
////
////    @Value("${security.jwt.secret-key}")
////	private String jwtSecretKey;
////
////    @Value("${security.jwt.secret-issuer}")
////	private String jwtIssuer;
////    
////    @Autowired
////    private AppUserRepository appUserRepository;
////    
////    
////    @PostMapping("/register")
////    public ResponseEntity<Object> register(
////    		 @Value(value = "") @RequestBody RegisterDto registerDto
////    		 ,BindingResult result) {
////    	
////    	
////    	 if (result.hasErrors()) {
////    		 var errorsList=result.getAllErrors();
////    		 var errorsMap =new HashMap<String, String>();
////    		 
////    		 for(int i=0; i<errorsList.size(); i++) {
////    			 var error=(FieldError)errorsList.get(i);
////    			 errorsMap.put(error.getField(), error.getDefaultMessage());
////    		 }
////    		 
////    		 
////    		 return ResponseEntity.badRequest().body(errorsMap);
////    	 }
////    	
////    	 
////    	 var bCryptEncoder =new BCryptPasswordEncoder();
////    	 
////    	 
////    	 AppUser appUser=new AppUser();
////    	 appUser.setFirstName(registerDto.getFirstname());
////    	 appUser.setLastName(registerDto.getLirstname());
////    	 appUser.setUsername(registerDto.getUsername());
////    	 appUser.setEmail(registerDto.getEmail());
////    	 appUser.setRole("client");
////    	 appUser.setCreatedAt(new Date());
////    	 appUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
////    	 
////    	 
////    	 try {
////    		 var otherUser =appUserRepository.findByUsername(registerDto.getUsername());
////    		 if(otherUser!= null) {
////    			 
////    		 return ResponseEntity.badRequest().body("Username already used");
////    		 }
////    		 otherUser=appUserRepository.findByEmail(registerDto.getEmail());
////    		 
////    		 if(otherUser!=null) {
////    			return ResponseEntity.badRequest().body("Email address already used"); 
////    		 }
////    		 appUserRepository.save(appUser);
////    		 
////    		 
////    		 String jwtToken=createToken(appUser);
////    		 
////    		 var response=new HashMap<String,Object>();
////    		 response.put("token", jwtToken);
////    		 response.put("user", appUser);
////    		 
////    		 
////    		 return ResponseEntity.ok(response);
////    		 
////    	 }catch(Exception ex) {
////    		 System.out.println("There is an Exception :");
////    		 ex.printStackTrace();
////    	 }
////    	return ResponseEntity.badRequest().body("Error");
////    	 
////    }
////  
////	
////	private String createToken(AppUser appUser) {
////    	Instant now = Instant.now();
////    	JwtClaimsSet claims = JwtClaimsSet.builder()
////    			.issuer(jwtIssuer)
////    			.issuedAt(now)
////    			.expiresAt(now.plusSeconds(24 * 3600))
////    			.subject(appUser.getUsername())
////    			.claim("role",appUser.getRole() )
////    			.build();
////    	
////    	var encoder = new NimbusJwtEncoder(
////    			new ImmutableSecret<>(jwtSecretKey.getBytes()));
////    	var params = JwtEncoderParameters.from(
////    			JwsHeader.with(MacAlgorithm.HS256).build(), claims);
////    	
////    	return encoder.encode(params).getTokenValue();
////    }
////}
//
//package com.example.demo.controller;
//
//import java.time.Instant;
//import java.util.Date;
//import java.util.HashMap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwsHeader;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.model.AppUser;
//import com.example.demo.model.LoginDto;
//import com.example.demo.model.RegisterDto;
//import com.example.demo.repository.AppUserRepository;
//import com.nimbusds.jose.jwk.source.ImmutableSecret;
//
//@RestController
//@RequestMapping("/account")
//public class AccountController {
//
//    @Value("${security.jwt.secret-key}")
//    private String jwtSecretKey;
//
//    @Value("${security.jwt.issuer}")
//    private String jwtIssuer;
//
//    @Autowired
//    private AppUserRepository appUserRepository;
//    
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/register")
//    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto, BindingResult result) {
//        if (result.hasErrors()) {
//           var errorsList = result.getAllErrors();
//           var errorsMap = new HashMap<String, String>();
//
//            for (int i = 0; i < errorsList.size(); i++) {
//                var error = (FieldError) errorsList.get(i);
//                errorsMap.put(error.getField(), error.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errorsMap);
//         
//            
//        }
//
//        var bCryptEncoder = new BCryptPasswordEncoder();
//       AppUser appUser = new AppUser();
//       appUser.setFirstName(registerDto.getFirstname());
//       appUser.setLastName(registerDto.getLirstname());
//        appUser.setUsername(registerDto.getUsername());
//        appUser.setEmail(registerDto.getEmail());
//       appUser.setRole("client");
//        appUser.setCreatedAt(new Date());
//        appUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));
//
//        try {
//            var otherUser = appUserRepository.findByUsername(registerDto.getUsername());
//            if (otherUser != null) {
//                return ResponseEntity.badRequest().body("Username already used");
//            }
//            otherUser = appUserRepository.findByEmail(registerDto.getEmail());
//
//            if (otherUser != null) {
//                return ResponseEntity.badRequest().body("Email address already used");
//           }
//           appUserRepository.save(appUser);
//            String jwtToken = createJwtToken(appUser);
//
//            var response = new HashMap<String, Object>();
//            response.put("token", jwtToken);
//           response.put("user", appUser);
//
//            return ResponseEntity.ok(response);
//
//        } catch (Exception ex) {
//            System.out.println("There is an Exception :");
//            ex.printStackTrace();
//        }
//        return ResponseEntity.badRequest().body("Error");
//
//    }
//    @PostMapping("/login")
//    public ResponseEntity<HashMap<String, Object>> login(
//    		@Value(value = "") @RequestBody LoginDto loginDto
//    		,BindingResult result){
//    	
//    	if (result.hasErrors()) {
//            var errorsList = result.getAllErrors();
//            var errorsMap = new HashMap<String, String>();
//
//             for (int i = 0; i < errorsList.size(); i++) {
//                 var error = (FieldError) errorsList.get(i);
//                 errorsMap.put(error.getField(), error.getDefaultMessage());
//             }
//          //  return ResponseEntity.badRequest().body(errorsMap);
//          
//    }
//    	try {
//    		authenticationManager.authenticate(
//    				new UsernamePasswordAuthenticationToken(
//    						loginDto.getUsername(),
//    						loginDto.getPassword()
//    						)
//    				);
//    		
//    		AppUser appUser=appUserRepository.findByUsername(loginDto.getUsername());
//    		
//    		String jwtToken= createJwtToken(appUser);
//    		var response = new HashMap<String ,Object>();
//    		response.put("token", jwtToken);
//    		response.put("user",appUser);
//    		
//    		return ResponseEntity.ok(response);
//    		
//    	}catch(Exception ex) {
//    		System.out.println("There is an exception :");
//    		ex.printStackTrace();
//    	}
//		//return null;
//    	
//    //  return ResponseEntity.badRequest().body("Bad username or password");
//    	}
//    
//    private String createJwtToken(AppUser appUser) {
//        Instant now = Instant.now();
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer(jwtIssuer)
//                .issuedAt(now)
//                .expiresAt(now.plusSeconds(24 * 3600))
//                .subject(appUser.getUsername())
//                .claim("role", appUser.getRole())
//                .build();
//
//        var encoder = new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecretKey.getBytes()));
//       var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
//
//        return encoder.encode(params).getTokenValue();
//    }
//}
package com.example.demo.controller;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AppUser;
import com.example.demo.model.LoginDto;
import com.example.demo.model.RegisterDto;
import com.example.demo.repository.AppUserRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Autowired
    private AppUserRepository appUserRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication auth){
    	var response=new HashMap<String ,Object>();
    	response.put("username", auth.getName());
    	response.put("Authorities", auth.getAuthorities());
    	
    	
    	var appUser=appUserRepository.findByUsername(auth.getName());
    	response.put("User", appUser);
    	
    	return ResponseEntity.ok(response);
    	
    }
    
    

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto, BindingResult result) {
        if (result.hasErrors()) {
            var errorsList = result.getAllErrors();
            var errorsMap = new HashMap<String, String>();

            for (int i = 0; i < errorsList.size(); i++) {
                var error = (FieldError) errorsList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        var bCryptEncoder = new BCryptPasswordEncoder();
        AppUser appUser = new AppUser();
        appUser.setFirstName(registerDto.getFirstname());
        appUser.setLastName(registerDto.getLirstname());
        appUser.setUsername(registerDto.getUsername());
        appUser.setEmail(registerDto.getEmail());
        appUser.setRole("client");
        appUser.setCreatedAt(new Date());
        appUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

        try {
            var otherUser = appUserRepository.findByUsername(registerDto.getUsername());
            if (otherUser != null) {
                return ResponseEntity.badRequest().body("Username already used");
            }
            otherUser = appUserRepository.findByEmail(registerDto.getEmail());

            if (otherUser != null) {
                return ResponseEntity.badRequest().body("Email address already used");
            }
            appUserRepository.save(appUser);
            String jwtToken = createJwtToken(appUser);

            var response = new HashMap<String, Object>();
            response.put("token", jwtToken);
            response.put("user", appUser);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            System.out.println("There is an Exception :");
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Error");
    }
@PostMapping("/login")
    public ResponseEntity<Object> login(
    	@Value(value = "") @RequestBody LoginDto loginDto
    	,BindingResult result){
	
	if(result.hasErrors()) {
		var errorsList=result.getAllErrors();
		var errorsMap=new HashMap<String,String>();
		
		

        for (int i = 0; i < errorsList.size(); i++) {
            var error = (FieldError) errorsList.get(i);
            errorsMap.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorsMap);
	}
	try {
	authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					loginDto.getUsername(),
					loginDto.getPassword()
					)
			);
	
		AppUser appUser=appUserRepository.findByUsername(loginDto.getUsername());
		
		String jwtToken =createJwtToken(appUser);
		
		var response=new  HashMap<String ,Object>();
		response.put("token", jwtToken);
		response.put("user",jwtToken );
		
		return ResponseEntity.ok(response);
		
	}catch(Exception ex) {
		System.out.println("There is a Exception:");
		ex.printStackTrace();
	
	}
		return ResponseEntity.badRequest().body("Bad username or password");
    	
    }

    private String createJwtToken(AppUser appUser) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(appUser.getUsername())
                .claim("role", appUser.getRole())
                .build();

        var encoder = new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        return encoder.encode(params).getTokenValue();
    }
}
