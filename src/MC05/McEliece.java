package MC05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Jama.Matrix;

public class McEliece {
	private static Matrix G;
	private static Matrix H;
	private static Matrix S;
	private static Matrix P;
	private static Matrix G1;
	private static Matrix e;
	private static Matrix y;
	private static Matrix y1;
	private static Matrix x1;
	private static Matrix x0;
	private static Matrix x;
	private final static int LENGTH = 4;

	public void generator(int type) {
		double[][] array = null;
		double[][] arrayH = null;
		if (type == 1) {
			array = new double[][] { { 1, 0, 0, 0, 1, 1, 0 },
					{ 0, 1, 0, 0, 1, 0, 1 }, { 0, 0, 1, 0, 0, 1, 1 },
					{ 0, 0, 0, 1, 1, 1, 1 } };
			arrayH = new double[][] { { 1, 1, 0 }, { 1, 0, 1 }, { 0, 1, 1 },
					{ 1, 1, 1 }, { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		}
		G = new Matrix(array);
		H = new Matrix(arrayH);
	}

	public void CreateSandP() {
		double[][] array = { { 1, 0, 0, 1 }, { 1, 1, 0, 1 }, { 0, 1, 0, 1 },
				{ 1, 1, 1, 0 } };
		S = new Matrix(array);
		double[][] array1 = { { 0, 0, 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0 } };
		P = new Matrix(array1);
	}

	private void Mod2(Matrix ma) {
		for (int i = 0; i < ma.getRowDimension(); i++)
			for (int j = 0; j < ma.getColumnDimension(); j++)
				ma.set(i, j, Math.abs(((int) ma.get(i, j)) % 2));
	}

	public void CaculateG1() {
		G1 = S.times(G).times(P);
		Mod2(G1);
	}

	public void GenerErrorVector() {
		double[][] array = { { 0, 1, 0, 0, 0, 0, 0 } };
		e = new Matrix(array);
	}

	public void CaculateCiphertext() {
		y = x.times(G1).plus(e);
		Mod2(y);
	}

	public void decrypt() {
		int ErrorPosition = 0;
		y1 = y.times(P.inverse());
		Mod2(y1);
		Matrix Syndrome = y1.times(H);// ����õ�У����
		Mod2(Syndrome);
		// ��У�����Աȣ�����У��
		for (int i = 0; i < H.getRowDimension(); i++) {
			boolean flag = true;
			for (int j = 0; j < H.getColumnDimension() && flag; j++) {
				if (H.get(i, j) != Syndrome.get(0, j))
					flag = false;
			}
			if (flag) {
				ErrorPosition = i;
				break;
			}
		}
		System.out.println("�����λ�ã�" + ErrorPosition);
		x1 = y1.copy();
		x1.set(0, ErrorPosition,
				(double) ((int) (y1.get(0, ErrorPosition)) + 1) % 2);
		System.out.print("x1��������");
		x1.print(0, 0);
		x0 = x1.getMatrix(0, 0, 0, LENGTH - 1);
		System.out.print("x0��������");
		x0.print(0, 0);
		System.out.print("���ܵõ����ģ�");
		x = x0.times(S.inverse());
		Mod2(x);
		x.print(0, 0);
	}

	// test
	public static void main(String args[]) {
		/**
		 * ���ɾ��� ��������ʹ��2��hamming��
		 */
		McEliece test = new McEliece();
		test.generator(1);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		test.CreateSandP();
		test.CaculateG1();
		test.GenerErrorVector();
		System.out.print("G1����(��Կ)������");
		G1.print(0, 0);
		System.out.print("e��������");
		e.print(0, 0);

		System.out.println("Alice: input message:");// ����
		try {
			String secret = br.readLine();
			double[][] array_x = new double[1][LENGTH];
			for (int i = 0; i < LENGTH; i++) {
				array_x[0][i] = Double.parseDouble(secret.charAt(i) + "");
			}

			x = new Matrix(array_x);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ����õ�������
		test.CaculateCiphertext();
		System.out.print("�õ������ģ�����");
		y.print(0, 0);

		// ����
		System.out.print("Bob���ܽ������");
		test.decrypt();
	}

}
