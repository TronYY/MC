/*   McEliece的实验
 * 
 *1.熟悉McEliece码的设计原理；
 *2.编程实现编码和译码算法；
 */

package MC05;

import java.util.Scanner;
import java.util.Random;

/*
 *  
 * @see 实现ME
 */
public class ME {
	static int k, m, n;
	Scanner sc;

	public static void main(String[] args) {
		ME MC = new ME();
		MC.start(MC);
	}

	public void start(ME MC) {
		System.out.print("请输入校验位数 k = ");
		sc = new Scanner(System.in);
		k = sc.nextInt();// 3
		n = (int) Math.pow(2, k) - 1;// 7
		m = n - k;// 4
		HammingCode HMC = new HammingCode();
		HMC.Create(HMC);// 得到H,G
		Matrix S = new Matrix();
		S = S.GenerateS();
		Matrix P = new Matrix();
		P = P.GenerateP();
		Matrix G = new Matrix(k, n);
		G.Mt = HMC.G;
		Matrix G1 = new Matrix();
		G1 = G1.MultipleMatrix(S, G);
		G1 = G1.MultipleMatrix(G1, P);
		System.out.println("公钥G1:");
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(G1.Mt[i][j] + " ");
			}
			System.out.println();
		}
		int send[] = new int[m];
		// 输入字符进行编码
		System.out.print("请输入需编码的字符（0、1）" + m + "个：");
		for (int i = 0; i < m; i++) {
			send[i] = sc.nextInt();
		}
		sc.close();
		int receive[] = this.Encrypt(send,HMC, G1);
		int send0[] =this.Decrypt(receive, P, S, HMC);
		for(int i=0 ; i<send0.length ; i++){
			if(send0[i]!=send[i]){
				System.out.println("解密错误！");
				return;
			}
		}
		System.out.println("解密正确");
		return;
	}

	public int[] Encrypt(int send[],HammingCode HMC, Matrix G1) {
		int receive[] = new int[n];
		int e;
		Random rnd = new Random();
		e = rnd.nextInt(n);   //e:0~n-1
		// 编码
		receive = HMC.encode(G1.Mt, send); 
		// 加入噪声
		receive[e] = (receive[e] + 1) % 2;
		// 打印y=x*G1+e
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
		
		// 利用校验矩阵H求得y1的最近码字
		int e1 = HMC.checkAndDecode(HMC.H, y1);//e1=H*y1
		y1[e1] = (y1[e1]+1)%2;
		for (int i = 0; i < n; i++) {
			//y1[i] = y1[i] ^ P.Mt[i][e1];
			if (i < m) {
				send[i] = y1[i];//取y1前m位放入send数组中
			}
		}
		S.Mt = S.Invert(S); //S^-1
		send = HMC.encode(S.Mt, send);//x0 = send * S^-1
		System.out.print("解密出的明文：");
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
		 *            Hamming码的具体实现
		 * @param HMC
		 *            类的实例
		 */
		public void Create(HammingCode HMC) {
			// Hamming 码的校验矩阵生成
			H = HMC.CreateCheckArray();
			// Hamming 码的生成矩阵生成
			G = HMC.GenerateArray();
		}

		/**
		 * @param ToBinary
		 *  校验矩阵 十进制数转为二进制矩阵
		 * @return 返回校验矩阵
		 */
		public int[][] CreateCheckArray() { // 二进制 校验矩阵
			int m1 = 1;
			int l = 0;
			int[][] b = new int[k][n];
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b[i].length; j++) {
					b[i][j] = 0;
				}
			}
			for (int i = 1; i <= n; i++) { // 二进制
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

			for (int j = 0; j < k; j++) { // 二进制
				for (int i = m; i < n; i++) {
					if (i - m == j) {
						b[j][i] = 1;
					}
				}
			}
			System.out.println("(" + n + "," + m + ")HammingCode校验矩阵生成:");
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
		 *            生成矩阵 十进制数转为二进制矩阵
		 * @return
		 */
		public int[][] GenerateArray() {// 生成矩阵 增加还没有做
			int g[][] = new int[k][m];
			int l = 0;
			int m1;
			for (int i = 1; i <= n; i++) { // 二进制
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

			// G 生成
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
			System.out.println("(" + n + "," + m + ")HammingCode生成矩阵生成:");
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
		 *            用生成矩阵编码
		 * @param G
		 *            代表生成矩阵
		 * @param send
		 *            输入的字符诸位 1、0 等
		 * @return
		 */
		public int[] encode(int[][] G, int[] send) { // 利用生成矩阵 编码

			int[] newArray = new int[G[1].length]; // 得到X*G的一个矩阵

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
		 *            校验矩阵错误
		 * @param receive
		 *            收到的信息
		 * @return 返回错误位置
		 */
		public int checkAndDecode(int[][] H, int[] receive) { // 检测错误
			int[] e = new int[H.length]; // H*x得到
			int flag ;
			for (int i = 0; i < e.length; i++) { // 得到Hx的一个矩阵
				e[i] = 0;
				for (int j = 0; j < receive.length; j++) {
					e[i] = e[i] ^ (receive[j] & H[i][j]);
				}
			}
			// 计算H*x
			// flag 得到位错的下标
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

		// 求行列式
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

		// 矩阵乘积
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

		// 高斯若而当列主元求逆矩阵
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

		// 求逆矩阵
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

		// 随机生成S
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
			System.out.println("S生成:");
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

		// 随机生成P；
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
			System.out.println("P生成:");

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