package MC03;

public class TestDLP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DLP dlp=new DLP();
		System.out.println("Diffie-Hellman体制:");
		dlp.Diffie_Hellman();
		
		System.out.println();
		System.out.println("ElGamal体制:");
		dlp.ElGamal();
		
	}

}
