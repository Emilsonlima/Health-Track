package br.com.Healthtrack.util;
import java.math.BigInteger;
import java.security.MessageDigest;

public class CriptografiaUtils {

			
		public static String criptografar(String nr_senha) throws Exception {
			
			MessageDigest md= MessageDigest.getInstance("MD5"); 
			
			md.update(nr_senha.getBytes("ISO-8859-1"));
			
			BigInteger hash= new BigInteger(1, md.digest());
			
			return hash.toString(16); 
	

	}
}
