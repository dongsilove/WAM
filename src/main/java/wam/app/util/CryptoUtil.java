package wam.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;

public class CryptoUtil {
	/**
	 * 비밀번호를 SHA256 방식으로 암호화한다.
	 * 
	 * @param password 비밀번호
	 * @return 비밀번호 문자열
	 */
	public static String encryptSHA256(String password) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			
			// 초기화
			messageDigest.reset();
			// 비밀번호 입력
			
			byte[] stringBytes = password.getBytes();
			
			int stringBytesLength = stringBytes.length;
			
			byte[] dataBytes = new byte[1024];
			// 암호화가능한 해시코드 생성
			for(int i = 0; i < stringBytesLength; i++){
				dataBytes[i] = stringBytes[i];
			}
			
			messageDigest.update(dataBytes, 0, stringBytesLength);
			
			byte[] encrypted = messageDigest.digest();
			
			// base64 인코딩
			byte[] base64Encoded = Base64.encodeBase64(encrypted);
			
			// 결과
			String result = new String(base64Encoded, CharEncoding.UTF_8);
			
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			return e.getLocalizedMessage();
		} catch(Exception e) {
			return e.getLocalizedMessage();
		}
	}
	
	/**
	 * 비밀번호를 SHA256 방식으로 암호화한다.
	 * 
	 * @param password 비밀번호
	 * @return 비밀번호 문자열
	 */
	public static String encryptSHA256salt(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
	/**
	 * SecureRandom의 값으로 SALT(byte16자리) 생성
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
    public static String getSalt() throws Exception {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        String value = salt.toString();
        return value;
    }


}
