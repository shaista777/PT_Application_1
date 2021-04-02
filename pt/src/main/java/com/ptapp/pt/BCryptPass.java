package com.ptapp.pt;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BCryptPass{
	
	private static final Logger logger = LoggerFactory.getLogger(BCryptPass.class);

	public String hashPassword(String plainTextPassword) {

		try {
			return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
		} catch (Exception ex) {
			logger.error(ex.toString());
			return ex.toString();
		}

	}

	public boolean matches(String plainPassword, String hashedPassword) {
		try {
			return (BCrypt.checkpw(plainPassword, hashedPassword));
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
	}

}
