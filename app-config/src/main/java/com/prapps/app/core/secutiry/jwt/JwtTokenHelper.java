package com.prapps.app.core.secutiry.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.jboss.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.prapps.app.core.dto.Role;
import com.prapps.app.core.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenHelper {
	private static final Logger LOG = Logger.getLogger(JwtTokenHelper.class);

	private static final String ISSUER = "apps-pratiks application";
	private static final String SIGNING_KEY = "LongAndHardToGuessValueWithSpecialCharacters";

	private static long durationInDays = 5000000l;
	//The JWT signature algorithm we will be using to sign the token
	private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	/**
	 * Creates a JSON Web Token which is digitally signed token and contains a
	 * payload (e.g. userId to identify the user). The signing key is secret
	 * which ensures that the token is authentic and has not been modified.
	 * Using a JWT eliminates the need to store authentication session
	 * information in a database.
	 *
	 * @param userId
	 * @param durationInDays
	 * @return
	 */

	public static String createJsonWebToken(Authentication auth) {
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SIGNING_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    Map<String, Object> claims = new HashMap<String, Object>();
	    claims.put("username", auth.getPrincipal());
	    claims.put("authorities", auth.getAuthorities());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
	                                .setIssuedAt(now)
	                                .setClaims(claims)
	                                .setIssuer(ISSUER)
	                                .signWith(signatureAlgorithm, signingKey);

	    //if it has been specified, let's add the expiration
	    if (durationInDays >= 0) {
	    long expMillis = nowMillis + durationInDays;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }

	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}

	public static User verifyToken(String token) {
	    Claims claims = Jwts.parser()
	    	.setAllowedClockSkewSeconds(100000000)
	       .setSigningKey(DatatypeConverter.parseBase64Binary(SIGNING_KEY))
	       .parseClaimsJws(token).getBody();
	    User user = new User();
	    user.setUserName(claims.get("username").toString());
	    Collection auths = (Collection)claims.get("authorities");
	    Set<Role> roles = new HashSet<Role>(auths.size());
	    for (Object obj : auths) {
	    	Map<String, Object> map = (Map<String, Object>) obj;
	    	Role role = new Role();
	    	role.setName(map.get("authority").toString());
	    	roles.add(role);
	    }
	    user.setRoles(roles);
	    return user;
	}

	public static void main(String args[]) {
		GrantedAuthority g = new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "ROLE_ADMIN";
			}
		};
		String token = createJsonWebToken(new UsernamePasswordAuthenticationToken("yogi", "password", Arrays.asList(g)));
		System.out.println(token);
		User user = verifyToken(token);
		System.out.println(user.getUserName());
	}

}
