package fr.test;

public class TestConstructor2  extends TestConstructor1 {
	{
		System.out.println("c");
	}
	public TestConstructor2() {
		System.out.println("d");
	}
}