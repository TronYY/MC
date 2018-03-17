package MidTest;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;


class IFP {
	private final static BigInteger ZERO = new BigInteger("0");
	private final static BigInteger ONE = new BigInteger("1");
	private final static BigInteger TWO = new BigInteger("2");
	private final static SecureRandom random = new SecureRandom();

	public static BigInteger rho(BigInteger N) {
		BigInteger divisor;
		BigInteger c = new BigInteger(N.bitLength(), random);
		BigInteger x = new BigInteger(N.bitLength(), random);
		BigInteger xx = x;
		if (N.mod(TWO).compareTo(ZERO) == 0) 
			return TWO;

		do {
			x = x.multiply(x).mod(N).add(c).mod(N);
			xx = xx.multiply(xx).mod(N).add(c).mod(N);
			xx = xx.multiply(xx).mod(N).add(c).mod(N);
			divisor = x.subtract(xx).gcd(N);
		}while((divisor.compareTo(ONE)) == 0);
		return divisor;
	}

	public static void factor(BigInteger N) {
		if (N.compareTo(ONE) == 0) 
			return;
		if (N.isProbablePrime(20)) {
			System.out.println(N);
			return;
		}
		BigInteger divisor = rho(N);
		factor(divisor);
		factor(N.divide(divisor));
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = null;
		scanner = new Scanner((System.in));
		BigInteger str = null;
		System.out.println("«Î ‰»Î ˝◊÷:");
		str = scanner.nextBigInteger();
		factor(str);
	}
}