package fr.bf;

import java.io.IOException;

public class BFEngine {

	public static void main(String[] args) throws IOException {
		final BFEngine engine = new BFEngine();
		engine.eval("++++++++++[->+++++++>++++++++++>+++>+++++++++<<<<]>++.>+.+++++++..+++.>++.>---.<<.+++.------.--------.>.+.");
	}

	private final InfiniteArray array = new InfiniteArray();

	public void eval(final String code) throws IOException {
		final int codeSize = code.length();
		int codeIndex = 0;
		while (codeIndex < codeSize) {
			char instruction = code.charAt(codeIndex);
			if (instruction == '+') {
				this.array.increment();
			} else if (instruction == '-') {
				this.array.decrement();
			} else if (instruction == '>') {
				this.array.shiftRight();
			} else if (instruction == '<') {
				this.array.shiftLeft();
			} else if (instruction == ',') {
				this.array.set(System.in.read());
			} else if (instruction == '.') {
				System.out.print((char) this.array.get());
			} else if (instruction == '[') {
				if (this.array.get() == 0) {
					int ouvert = 0;
					while (!(ouvert == -1 && instruction == ']')) {
						codeIndex++;
						instruction = code.charAt(codeIndex);
						if (instruction == '[') {
							ouvert++;
						} else if (instruction == ']') {
							ouvert--;
						}
					}
				}
			} else if (instruction == ']') {
				if (this.array.get() != 0) {
					int ouvert = 0;
					while (!(ouvert == -1 && instruction == '[')) {
						codeIndex--;
						instruction = code.charAt(codeIndex);
						if (instruction == ']') {
							ouvert++;
						} else if (instruction == '[') {
							ouvert--;
						}
					}
				}
			}
			codeIndex++;
		}
	}
}