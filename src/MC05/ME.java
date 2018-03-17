/*   McEliece��ʵ��
 * 
 *1.��ϤMcEliece������ԭ��
 *2.���ʵ�ֱ���������㷨��
 */

package MC05;

import java.util.Scanner;
import java.util.Random;

/*
 *  
 * @see ʵ��ME
 */
public class ME {
	static int k, m, n;
	Scanner sc;

	public static void main(String[] args) {
		ME MC = new ME();
		MC.start(MC);
	}

	public void start(ME MC) {
		System.out.print("������У��λ�� k = ");
		sc = new Scanner(System.in);
		k = sc.nextInt();// 3
		n = (int) Math.pow(2, k) - 1;// 7
		m = n - k;// 4
		HammingCode HMC = new HammingCode();
		HMC.Create(HMC);// �õ�H,G
		Matrix S = new Matrix();
		S = S.GenerateS();
		Matrix P = new Matrix();
		P = P.GenerateP();
		Matrix G = new Matrix(k, n);
		G.Mt = HMC.G;
		Matrix G1 = new Matrix();
		G1 = G1.MultipleMatrix(S, G);
		G1 = G1.MultipleMatrix(G1, P);
		System.out.println("��ԿG1:");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(G1.Mt[i][j] + " ");
			}
			System.out.println();
		}
		int send[] = new int[m];
		// �����ַ����б���
		System.out.print("�������������ַ���0��1��" + m + "����");
		for (int i = 0; i < m; i++) {
			send[i] = sc.nextInt();
		}
		sc.close();
		int receive[] = this.Encrypt(send,HMC, G1);
		int send0[] =this.Decrypt(receive, P, S, HMC);
		for(int i=0 ; i<send0.length ; i++){
			if(send0[i]!=send[i]){
				System.out.println("���ܴ���");
				return;
			}
		}
		System.out.println("������ȷ");
		return;
	}

	public int[] Encrypt(int send[],HammingCode HMC, Matrix G1) {
		int receive[] = new int[n];
		int e;
		Random rnd = new Random();
		e = rnd.nextInt(n);   //e:0~n-1
		// ����
		receive = HMC.encode(G1.Mt, send); 
		// ��������
		receive[e] = (receive[e] + 1) % 2;
		// ��ӡy=x*G1+e
		System.out.print("y=x*G1+e:");
		for (int i = 0; i < n; i++) {
			System.out.print(receive[i] + " ");
		}
		System.out.println();
		return receive;
	}

	public int[] Decrypt(int receive[], Matrix P, Matrix S, HammingCode HMC) {
		int[] send = new int[m];
		int[] y1 = new int[n];
		P.Mt = P.Invert(P);
		y1 = HMC.encode(P.Mt, receive);// y1=y*p^-1
		
		// ����У�����H���y1���������
		int e1 = HMC.checkAndDecode(HMC.H, y1);//e1=H*y1
		y1[e1] = (y1[e1]+1)%2;
		for (int i = 0; i < n; i++) {
			//y1[i] = y1[i] ^ P.Mt[i][e1];
			if (i < m) {
				send[i] = y1[i];//ȡy1ǰmλ����send������
			}
		}
		S.Mt = S.Invert(S); //S^-1
		send = HMC.encode(S.Mt, send);//x0 = send * S^-1
		System.out.print("���ܳ������ģ�");
		for (int i = 0; i < m; i++) {
			System.out.print(send[i]+" ");
		}
		return send;
	}

	class HammingCode {

		int[][] H;
		int[][] G;

		/**
		 * @param start
		 *            Hamming��ľ���ʵ��
		 * @param HMC
		 *            ���ʵ��
		 */
		public void Create(HammingCode HMC) {
			// Hamming ���У���������
			H = HMC.CreateCheckArray();
			// Hamming ������ɾ�������
			G = HMC.GenerateArray();
		}

		/**
		 * @param ToBinary
		 *  У����� ʮ������תΪ�����ƾ���
		 * @return ����У�����
		 */
		public int[][] CreateCheckArray() { // ������ У�����
			int m1 = 1;
			int l = 0;
			int[][] b = new int[k][n];
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b[i].length; j++) {
					b[i][j] = 0;
				}
			}
			for (int i = 1; i <= n; i++) { // ������
				m1 = i;
				if (i != 1 && i != 2 && i != 4 && i != 8) {
					for (int j = k - 1; j >= 0; j--) {
						b[j][l] = m1 % 2;
						m1 = m1 / 2;
						if (m1 < 0) {
							break;
						}
					}
					l++;
				}
			}

			for (int j = 0; j < k; j++) { // ������
				for (int i = m; i < n; i++) {
					if (i - m == j) {
						b[j][i] = 1;
					}
				}
			}
			System.out.println("(" + n + "," + m + ")HammingCodeУ���������:");
			System.out
					.println("----------------------------------------------------------");
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(b[i][j] + " ");
				}
				System.out.println();
			}
			System.out
					.println("----------------------------------------------------------");

			return b;
		}

		/**
		 * @param Generate
		 *            ���ɾ��� ʮ������תΪ�����ƾ���
		 * @return
		 */
		public int[][] GenerateArray() {// ���ɾ��� ���ӻ�û����
			int g[][] = new int[k][m];
			int l = 0;
			int m1;
			for (int i = 1; i <= n; i++) { // ������
				m1 = i;
				if (i != 1 && i != 2 && i != 4 && i != 8) {
					for (int j = k - 1; j >= 0; j--) {
						g[j][l] = m1 % 2;
						m1 = m1 / 2;
						if (m1 < 0) {
							break;
						}
					}
					l++;
				}
			}

			// G ����
			int[][] G = new int[m][n];
			for (int i = 0; i < G.length; i++) {
				for (int j = 0; j < G[i].length; j++) {
					if (j == i) {
						G[i][j] = 1;
					} else {
						G[i][j] = 0;
					}
				}
			}

			for (int j = m; j < n; j++) {
				for (int i = 0; i < G.length; i++) {
					G[i][j] = g[j - m][i];
				}
			}
			System.out.println("(" + n + "," + m + ")HammingCode���ɾ�������:");
			System.out
					.println("----------------------------------------------------------");
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(G[i][j] + " ");
				}
				System.out.println();
			}
			System.out
					.println("----------------------------------------------------------");
			return G;
		}

		/**
		 * @param encode
		 *            �����ɾ������
		 * @param G
		 *            �������ɾ���
		 * @param send
		 *            ������ַ���λ 1��0 ��
		 * @return
		 */
		public int[] encode(int[][] G, int[] send) { // �������ɾ��� ����

			int[] newArray = new int[G[1].length]; // �õ�X*G��һ������

			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = 0;
				for (int j = 0; j < G.length; j++)
					newArray[i] = (newArray[i]+send[j] * G[j][i])%2;
			}
			return newArray;
		}

		/**
		 * 
		 * @param H
		 *            У��������
		 * @param receive
		 *            �յ�����Ϣ
		 * @return ���ش���λ��
		 */
		public int checkAndDecode(int[][] H, int[] receive) { // ������
			int[] e = new int[H.length]; // H*x�õ�
			int flag ;
			for (int i = 0; i < e.length; i++) { // �õ�Hx��һ������
				e[i] = 0;
				for (int j = 0; j < receive.length; j++) {
					e[i] = e[i] ^ (receive[j] & H[i][j]);
				}
			}
			// ����H*x
			// flag �õ�λ����±�
			flag = -1;
			for (int i = 0; i < receive.length; i++) {
				int k = 0;
				for (int j = 0; j < e.length; j++) {
					if(e[j] == H[j][i]){
						k++;
					} else{
						break;
					}
				}
				if(k == e.length){
					flag = i;
					break;
				}
			}
			return flag;
		}

	}

	class Matrix {
		int r, c;
		int[][] Mt;

		Matrix() {
		}

		Matrix(int x, int y) {
			r = x;
			c = y;
			Mt = new int[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					Mt[i][j] = 0;
				}
			}
		}

		// ������ʽ
		public int Det(Matrix M) {
			int switchtime = 0, result;
			int t[][] = new int[M.c][M.c];
			for (int i = 0; i < M.c; i++) {
				for (int j = 0; j < M.c; j++) {
					t[i][j] = M.Mt[i][j];
				}
			}
			for (int row = 0; row < M.c - 1; row++) {
				int nextrow = row + 1;
				if (t[row][row] == 0) {
					while (t[nextrow][row] == 0 && nextrow < M.c - 1) {
						nextrow++;
					}
					if (nextrow == M.c - 1) {
						return 0;
					} else {
						switchtime++;
						for (int col = 0; col < M.c; col++) {
							int temp1 = t[row][col];
							t[row][col] = t[nextrow][col];
							t[nextrow][col] = temp1;
						}
					}
				}
				for (nextrow = row + 1; nextrow < M.c; nextrow++) {
					if (t[nextrow][row] != 0) {
						int temp2 = t[nextrow][row] / t[row][row];
						for (int col = row; col < M.c; col++) {
							t[nextrow][col] += -temp2 * t[row][col];
						}
					} else {
						nextrow++;
					}
				}
			}
			result = 1;
			for (int row = 0; row < M.c; row++) {
				result *= t[row][row];
			}
			if (switchtime % 2 == 1) {
				return -result;
			} else {
				return result;
			}
		}

		// ����˻�
		public Matrix MultipleMatrix(Matrix A, Matrix B) {
			Matrix C = new Matrix(A.r, B.c);
			for (int i = 0; i < A.r; i++) {
				for (int j = 0; j < B.c; j++) {
					for (int k = 0; k < A.c; k++) {
						C.Mt[i][j] += A.Mt[i][k] * B.Mt[k][j];
					}
					C.Mt[i][j] = C.Mt[i][j] % 2;
				}
			}
			return C;
		}

		// ��˹����������Ԫ�������
		public int[][] Max(int[][] a, int k) {
			int max, tmp;
			int m, j;
			max = a[k][k];
			m = k;
			for (j = k + 1; j < a.length; j++) {
				if (Math.abs(a[j][k]) > Math.abs(max)) {
					max = a[j][k];
					m = j;
				}
			}
			for (j = k; j < a[0].length; j++) {
				tmp = a[k][j];
				a[k][j] = a[m][j];
				a[m][j] = tmp;
			}
			return a;
		}

		public void GaussJordanlie(Matrix a) {
			int i, j;
			for (int k1 = 0; k1 < a.r; k1++) {
				a.Mt = Max(a.Mt, k1);
				for (j = a.c - 1; j >= k1; j--)
					a.Mt[k1][j] = a.Mt[k1][j] / a.Mt[k1][k1];
				for (i = 0; i < a.r; i++) {
					if (i == k1)
						continue;
					for (j = k1 + 1; j < a.c; j++)
						a.Mt[i][j] = a.Mt[i][j] - a.Mt[i][k1] * a.Mt[k1][j];
					a.Mt[i][k1] = 0;
				}
			}
		}

		// �������
		public int[][] Invert(Matrix a) {
			int c = 2 * a.r;
			Matrix b = new Matrix(a.r, c);
			for (int i = 0; i < a.r; i++) {
				for (int j = 0; j < c; j++) {
					if (j < a.r) {
						b.Mt[i][j] = a.Mt[i][j];
					} else {
						if (j == a.r + i) {
							b.Mt[i][j] = 1;
						} else {
							b.Mt[i][j] = 0;
						}
					}
				}
			}
			GaussJordanlie(b);
			for (int i = 0; i < a.r; i++) {
				for (int j = a.r; j < c; j++) {
					a.Mt[i][j - a.r] = b.Mt[i][j] % 2;
					if (a.Mt[i][j - a.r] == -1) {
						a.Mt[i][j - a.r] = 1;
					}
				}
			}
			return a.Mt;
		}

		// �������S
		public Matrix GenerateS() {
			long seed = 0;
			Matrix S = new Matrix(m, m);
			do {
				Random rnd = new Random(seed);
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < m; j++) {
						S.Mt[i][j] = rnd.nextInt(2);
					}
				}
				seed = seed + 1;
			} while (Det(S) == 0);
			System.out.println("S����:");
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(S.Mt[i][j] + " ");
				}
				System.out.println();
			}
			System.out
					.println("----------------------------------------------------------");
			return S;
		}

		// �������P��
		public Matrix GenerateP() {
			Matrix P = new Matrix(n, n);
			int t[] = new int[n];
			for (int i = 0; i < n; i++) {
				t[i] = 0;
			}
			Random rnd = new Random();
			for (int j = 0; j < n; j++) {
				int k = rnd.nextInt(n);
				while (t[k] != 0) {
					k++;
					if (k == n) {
						k = 0;
					}
				}
				P.Mt[k][j] = 1;
				t[k] = 1;
			}
			System.out.println("P����:");

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(P.Mt[i][j] + " ");
				}
				System.out.println();
			}
			System.out
					.println("----------------------------------------------------------");
			return P;
		}
	}

}