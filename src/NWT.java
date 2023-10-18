import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NWT {

	static Scanner input = new Scanner(System.in);

	public static GUI gui = new GUI();

	public static float[] a = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static float[] b = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static float[] a1 = new float[10];
	public static float[] b1 = new float[10];

	public static int genau = 10;

	public static HashMap<Integer, Float> Xtabelle = new HashMap<Integer, Float>();
	public static HashMap<Integer, Float> Ytabelle = new HashMap<Integer, Float>();

	public static ArrayList<Newton> newtons = new ArrayList<Newton>();
	public static ArrayList<Float> ergebnisse = new ArrayList<Float>();

	public static void main(String[] args) {
		start();
	}

	static void start() {
		gui.start();
		System.out.println("a = multiplier");
		System.out.println("b = potenz");

		String input1 = "";
		int input2 = 0;
		float input3 = 0;

		while (true) {

			try {
				input1 = "";
				input2 = 0;
				input3 = 0;
				input1 = input.next();
				input2 = input.nextInt();
				input3 = input.nextFloat();

				if (input1.equals("start")) {
					rechnen();
				}
				if (input1.equals("clear")) {
					for (int i = 0; i < 10; i++) {
						a[i] = 0;
						b[i] = 0;
					}
					System.out.println(Arrays.toString(a));
					System.out.println(Arrays.toString(b));
				}
				if (input1.equals("exit")) {
					System.out.println("yeet2");
					System.exit(0);
				}
				if (input1.equals("a")) {
					a[input2 - 1] = input3;
					System.out.println("a (Nummer: " + input2 + ") auf " + input3 + " gesetzt");
					System.out.println(Arrays.toString(a));
				}
				if (input1.equals("b")) {
					b[input2 - 1] = input3;
					System.out.println("b (Nummer: " + input2 + ") auf " + input3 + " gesetzt");
					System.out.println(Arrays.toString(b));
				}
			} catch (Exception e) {
			}
		}
	}

	static void rechnen() {
		ergebnisse.clear();
		newtons.clear();
		Ytabelle.clear();
		Xtabelle.clear();

		bildeAbleitung();

		for (int i = 0; i < 200; i++) {
			Xtabelle.put(i, (float) (0.1 * i - 10));
			Ytabelle.put(i, (float) (f((float) (0.1 * i - 10))));
		}

		for (int i = 0; i < 199; i++) {
			if (Ytabelle.get(i) < 0) {
				if (Ytabelle.get(i + 1) > 0) {
					// System.out.println("alarm" + ((float) (0.1 * (i + 0.5) -10)));
					newtons.add(new Newton((float) (0.1 * (i + 0.5) - 10)));
				}
			} else if (Ytabelle.get(i) > 0) {
				if (Ytabelle.get(i + 1) < 0) {
					// System.out.println("alarm" + ((float) (0.1 * (i + 0.5) -10)));
					newtons.add(new Newton((float) (0.1 * (i + 0.5) - 10)));
				}
			} else if (Ytabelle.get(i) == 0) {
				// System.out.println("alarm" + ((float) (0.1 * i -10)));
				ergebnisse.add(0f);
			}
		}

		for (int i = 0; i < newtons.size(); i++) {
			newtons.get(i).rechne(i);
		}

		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Ergebnisse sind bei Rund:");
		System.out.println();
		for (int i = 0; i < ergebnisse.size(); i++) {
			System.out.println((i + 1) + ": (" + ergebnisse.get(i) + " / 0)");
		}
	}

	static void bildeAbleitung() {
		for (int i = 0; i < 10; i++) {
			a1[i] = a[i] * b[i];
			if (b[i] > 0) {
				b1[i] = b[i] - 1;
			}
		}
	}

	public static float f(float x) {
		float back = 0;
		for (int i = 0; i < 10; i++) {
			back += a[i] * Math.pow(x, b[i]);
		}
		return back;
	}

	public static float f1(float x) {
		float back = 0;
		for (int i = 0; i < 10; i++) {
			back += a1[i] * Math.pow(x, b1[i]);
		}
		return back;
	}

	public static void mouseDragged(int x, int y) {
		gui.MouseDragged(x, y);
	}

	public static void clicked() {
		gui.click();
	}

	public static void push(char c) {
		gui.push(c);
	}

	public static void del() {
		gui.del();
	}
}