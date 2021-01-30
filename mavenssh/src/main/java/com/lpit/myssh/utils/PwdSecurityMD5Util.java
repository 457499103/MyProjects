package com.lpit.myssh.utils;

import org.springframework.util.DigestUtils;

public class PwdSecurityMD5Util {
	

	
	
	    /**
	     * Method name: md5 <BR>
	     * Description: 加密密码 <BR>
	     * Remark: <BR>
	     * @param text 明文
	     * @return  String 密文<BR>
	     */
	    public static String md5(String text){
	        //加密后的字符串
	        String encodeStr=DigestUtils.md5DigestAsHex(text.getBytes());
	        return encodeStr;
	   }

	
        public static boolean verify(String text, String md5){
        //根据传入的密钥进行验证
        String md5Text =  md5(text);
        if(md5Text.equalsIgnoreCase(md5)){
            return true;
        }
        return false;
   }



/*    public static void main(String[] args) {

    	String pwd = "123456";
    	String md5= PwdSecurityMD5Util.md5(pwd);
    	
    	System.out.println(md5);
    	System.out.println(PwdSecurityMD5Util.verify(pwd, md5));
    	System.out.println(md5.length());
		
	}*/
	
}
