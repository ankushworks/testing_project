package com.Social11.security;

public class CustomUserDetails{

//	@Autowired
//	IuserRepository userRepository;
//
//	String User;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		UserEntity user=userRepository.findByusername(username);
//		System.out.println(user);
//		User=user.getUsername();
//		
//		if(user!=null) {
//			return new User(user.getUsername(),user.getPassword(), new ArrayList<>()); 
//		}
//		else{
//			throw new UsernameNotFoundException("User not Found");
//		}
////		if(username.equals("harmeet_singh")) {
////			return new User("harmeet_singh","Harmeet1234",new ArrayList<>());
////		}
////		else {
////			throw new UsernameNotFoundException("User not found!!");
////		}
//	}
//	
//		public String currentLoggedInUser() {
//			return User;
//		}
//
////	@Override
////	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,DataAccessException {
////		// TODO Auto-generated method stub
////		
////		CurrentUser user = null;
////		List authorities = new ArrayList();
////		
////		UserEntity currentuser = userRepository.findByusername(username);
////		
////		return null;
////	}
	
}
