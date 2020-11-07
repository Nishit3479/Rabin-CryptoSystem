package rabin.crypto.project;

import java.util.*;
import java.io.*;
import java.math.BigInteger; 
import java.nio.charset.Charset; 
import java.security.SecureRandom; 
import java.util.Random;

class Encryption { 
	public static Random r = new SecureRandom(); 
	public static BigInteger TWO = BigInteger.valueOf(2); 
	public static BigInteger THREE = BigInteger.valueOf(3); 
	public static BigInteger FOUR = BigInteger.valueOf(4); 

	public static BigInteger[] generateKey(int bitLength) 
	{ 
		BigInteger p = blumPrime(bitLength / 2); 
		BigInteger q = blumPrime(bitLength / 2); 
		BigInteger N = p.multiply(q); 
		return new BigInteger[] { N, p, q }; 
	} 

	public static BigInteger encrypt(BigInteger m, 
									BigInteger N) 
	{ 
		return m.modPow(TWO, N); 
	}  

	public static BigInteger[] Gcd(BigInteger a, BigInteger b) 
	{ 
		BigInteger s = BigInteger.ZERO; 
		BigInteger old_s = BigInteger.ONE; 
		BigInteger t = BigInteger.ONE; 
		BigInteger old_t = BigInteger.ZERO; 
		BigInteger r = b; 
		BigInteger old_r = a; 
		while (!r.equals(BigInteger.ZERO)) { 
			BigInteger q = old_r.divide(r); 
			BigInteger tr = r; 
			r = old_r.subtract(q.multiply(r)); 
			old_r = tr; 

			BigInteger ts = s; 
			s = old_s.subtract(q.multiply(s)); 
			old_s = ts; 

			BigInteger tt = t; 
			t = old_t.subtract(q.multiply(t)); 
			old_t = tt; 
		} 
		return new BigInteger[] { old_r, old_s, old_t }; 
	} 

	public static BigInteger blumPrime(int bitLength) 
	{ 
		BigInteger p; 
		do { 
			p = BigInteger.probablePrime(bitLength, r); 
		} while (!p.mod(FOUR).equals(THREE)); 
		return p; 
	} 
}
