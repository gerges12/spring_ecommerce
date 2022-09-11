package com.luv2code.ecommerce.exceptions;

import java.security.GeneralSecurityException;

public class ecommmerceException  extends  RuntimeException {

	public ecommmerceException(String string, Exception e) {
		super (string , e)  ;
	} 
	
	
	public ecommmerceException(String exMessage) {
        super(exMessage);
    }

}
