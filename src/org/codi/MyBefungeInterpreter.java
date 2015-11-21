package org.codi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class MyBefungeInterpreter {
	
	public static void main(String[] args) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("89*,25*:*1+:,7+::,,3+,48*,v\n");
		buffer.append("v-6,:+3,:+1*+1:*52,-1*+658<\n");
		buffer.append(">:,8-,48*:,1+,25*,@");
		eval(buffer.toString());
		eval("&&+.@");
	}

	public static void eval(String instructions) {
		Navigator navigator = new Navigator(instructions);
		Indexer index = new Indexer();
		Deque<Integer> pile = new ArrayDeque<Integer>();
		char c = navigator.charAt(index);
		while (c != '@') {
			switch (c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				pile.addLast(c - 48);
				break;
			case '+':
				pile.addLast(pile.pollLast() + pile.pollLast());
				break;
			case '-':
				int a = pile.pollLast();
				int b = pile.pollLast();
				pile.addLast(b - a);
				break;
			case '*':
				pile.addLast(pile.pollLast() * pile.pollLast());
				break;
			case '/':
				int d = pile.pollLast();
				int e = pile.pollLast();
				pile.addLast(e / d);
				break;
			case '%':
				int f = pile.pollLast();
				int g = pile.pollLast();
				pile.addLast(g % f);
				break;
			case '!':
				pile.addLast(pile.pollLast() == 0 ? 1 : 0);
				break;
			case '`':
				int h = pile.pollLast();
				int i = pile.pollLast();
				pile.addLast(i > h ? 1 : 0);
				break;
			case '>':
				index.right();
				break;
			case '<':
				index.left();
				break;
			case 'v':
			case 'V':
				index.down();
				break;
			case '^':
				index.up();
				break;
			case '?':
				int j = new Random().nextInt(4);
				switch (j) {
				case 0:
					index.right();
					break;
				case 1:
					index.left();
					break;
				case 2:
					index.down();
					break;
				default:
					index.up();
					break;
				}
				break;
			case '_':
				if (pile.pollLast() == 0) {
					index.right();
				} else {
					index.left();
				}
				break;
			case '|':
				if (pile.pollLast() == 0) {
					index.down();
				} else {
					index.up();
				}
				break;
			case ':':
				pile.addLast(pile.peekLast());
				break;
			case '\\':
				int k = pile.pollLast();
				int l = pile.pollLast();
				pile.addLast(k);
				pile.addLast(l);
				break;
			case '$':
				pile.pollLast();
				break;
			case '.':
				System.out.print(pile.pollLast());
				break;
			case ',':
				System.out.print((char) (int) pile.pollLast());
				break;
			case '#':
				index.increment();
				break;
			case '&':
				try {
					pile.addLast(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
				} catch (Exception ex) {
					ex.printStackTrace();
					pile.addLast(0);
				}
				break;
			case '~':
				try {
					pile.addLast((int) new BufferedReader(new InputStreamReader(System.in)).readLine().charAt(0));
				} catch (Exception ex) {
					ex.printStackTrace();
					pile.addLast(0);
				}
				break;
			default:
				// all other char are nop
				break;
			}
			index.increment();
			c = navigator.charAt(index);
		}
	}

	private static class Navigator {

		private String[] instructions;

		public Navigator(String instructions) {
			this.instructions = instructions.split("\n");
		}

		public char charAt(Indexer index) {
			return this.instructions[index.getY()].charAt(index.getX());
		}
	}

	private static class Indexer {
		private int x;
		private int y;
		private boolean horizontal;
		private boolean positif;

		public Indexer() {
			this.x = 0;
			this.y = 0;
			this.horizontal = true;
			this.positif = true;
		}

		public void increment() {
			int increment = (positif) ? 1 : -1;
			if (horizontal) {
				this.x += increment;
			} else {
				this.y += increment;
			}
		}

		public void up() {
			this.horizontal = false;
			this.positif = false;
		}

		public void down() {
			this.horizontal = false;
			this.positif = true;
		}

		public void right() {
			this.horizontal = true;
			this.positif = true;
		}

		public void left() {
			this.horizontal = true;
			this.positif = false;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}

	}
	
}
