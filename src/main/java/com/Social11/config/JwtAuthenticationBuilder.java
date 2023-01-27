package com.Social11.config;

public class JwtAuthenticationBuilder {
//
//	@Autowired
//	private Jwtutil jwtutil;
//	
//	@Autowired
//	private CustomUserDetails customuserdetailservice;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String requestTokenHeader = request.getHeader("Authorization");
//		String username = null;
//		String jwtToken = null;
//		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
//			jwtToken = requestTokenHeader.substring(7);
//			try {
//					
//				username=this.jwtutil.getUsernameFromToken(jwtToken);
//					
//			}
//			catch(Exception e) {
//				
//			}
//			System.out.println("lets see "+username);
//
//			UserDetails userDetails= this.customuserdetailservice.loadUserByUsername(username);
//
//			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
//				
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken	= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//				
//			}
//			else {
//				System.out.println("Token is not validated");
//			}
//			
//		}
//		filterChain.doFilter(request, response);
//	}
	

	
}
