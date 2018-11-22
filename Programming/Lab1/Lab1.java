import static java.lang.Math.*;

/**
 * @author Anatolii Anishchenko
 * @group P3112
 */

public class Lab1 {
	public static void main(String[] args) {
		//issue1
		final int F_SIZE = 7;
		long[] f = { 18l, 16l, 14l, 12l, 10l, 8l, 6l };

		//issue2
		final int X_SIZE = 11;
		final float UP_BOUND = 7.0f;
		final float DOWN_BOUND = -2.0f;
		float[] x = new float[X_SIZE];
		for (int i = 0; i < X_SIZE; i++) {
			x[i] = ((float) random()) * (UP_BOUND - DOWN_BOUND) + DOWN_BOUND;
		}

		//issue3
		double[][] e = new double[F_SIZE][X_SIZE];
		for (int i = 0; i < F_SIZE; i++) {
			for (int j = 0; j < X_SIZE; j++) {
				switch (f[i]) {
				case 10:
					e[i][j] = atan(sin(atan(sin(x[j]))));
					break;
				case 6:
				case 12:
				case 16:
					e[i][j] = pow(asin(exp(-abs(x[j]))) + 1, cos(asin((x[j] + 2.5) / 9)));
					break;
				default:
					e[i][j] = asin(pow(cos(sin(pow((double) (2 - x[j]) / 4, 2))), 2));
					break;
				}
				//if (f[i] == 10) {
				//	e[i][j] = atan(sin(atan(sin(x[j]))));
				//} else if (f[i] == 6 || f[i] == 12 || f[i] == 16) {
				//	e[i][j] = pow(asin(exp(-abs(x[j]))) + 1, cos(asin((x[j] + 2.5) / 9)));
				//} else {
				//	e[i][j] = asin(pow(cos(sin(pow((double) (2 - x[j]) / 4, 2))), 2));
				//}
			}
		}

		//issue4
		for (int i = 0; i < F_SIZE; i++) {
			for (int j = 0; j < X_SIZE; j++) {
				System.out.printf("%7.4f ", e[i][j]);
			}

			System.out.println();
		}
	}
}
