package MC06;

public class TestECC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ECC ecc=new ECC();
		System.out.println("Diffie-Hellman体制:");
		ecc.Diffie_Hellman();
		
		System.out.println();
		System.out.println("ElGamal体制:");
		ecc.ElGamal();
		
	}

}
