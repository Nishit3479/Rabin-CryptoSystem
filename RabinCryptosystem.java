package rabin.crypto.project;

import java.util.*;
import java.io.*;
import java.math.BigInteger; 
import java.nio.charset.Charset; 
import java.security.SecureRandom; 
import java.util.Random; 
 
public class RabinCryptosystem { 
	public static void main(String[] args) 
	{ 
		Encryption enc = new Encryption();
		Decryption dcr = new Decryption();
		Scanner in = new Scanner(System.in);
		String finalMessage = null;
		int i = 1; 
		System.out.println("Choose Option :\n1.Encryption\n2.Decryption");
		int a = in.nextInt();
		if(a==1)
		{	
			BigInteger[] key = enc.generateKey(512);
			BigInteger n = key[0]; 
			BigInteger p = key[1]; 
			BigInteger q = key[2]; 
			System.out.println("Enter Message : ");
			String s = in.nextLine();
			BigInteger m = new BigInteger(s.getBytes(Charset.forName("ascii"))); 
			BigInteger c = enc.encrypt(m, n); 

			System.out.println("Encrypted Message : " + c);
			System.out.println("Key 1 : " + key[1]);
			System.out.println("Key 2 : " + key[2]);
		}
		else if(a==2)
		{
			System.out.println("Enter the Cipher Text :");
			String str1 = in.nextLine();
			BigInteger s1 = new BigInteger(str1);
			System.out.println("Enter the 1st Key :");
			String str2 = in.nextLine();
			BigInteger s2 = new BigInteger(str2);
			System.out.println("Enter the 2nd Key :");
			String str3 = in.nextLine();
			BigInteger s3 = new BigInteger(str3);
			BigInteger[] m2 = dcr.decrypt(s1, s2, s3); 
			for (BigInteger b : m2) { 
				String dec = new String(b.toByteArray(),Charset.forName("ascii")); 
				if (dec.equals(s1)) { 
					finalMessage = dec; 
				} 
				i++; 
			} 
			System.out.println("Decrypted Message : "+ finalMessage); 
		}
		else
		{
			System.out.print("Invalid Choice");
		}
	} 
} 
