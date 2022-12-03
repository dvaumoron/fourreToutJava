<%= packageStmt =%>

public class <%= name =%> {

	public static void main(String[] args) {
		char c = 'a';
		System.out.println(c);

		final SquareMatrix3 m = new SquareMatrix3(new double[]{<% for (var i = 1 ; i < 10; i++) { if (i != 1) { %>, <% } %><%= i =%><% } %>});
		final ColumnMatrix3 cm = new ColumnMatrix3(new double[]{1,2,3});
		System.out.println("m=");
		System.out.println(m);
		System.out.println("m.transpose()=");
		System.out.println(m.transpose());
		System.out.println("cm=");
		System.out.println(cm);
		System.out.println("m*cm=");
		System.out.println(m.multiply(cm));
		System.out.println("m+m=");
		System.out.println(m.add(m));
		System.out.println("m*3=");
		System.out.println(m.multiply(3));
		System.out.println("m*m=");
		System.out.println(m.multiply(m));
		System.out.println("sigmoid(cm)=");
		System.out.println(cm.applySigmoid());

		final SquareMatrix2 m2 = new SquareMatrix2(new double[]{2,-1,-1,2});
		final SquareMatrix2 m3 = new SquareMatrix2(new double[]{5,-4,4,-5});
		final SquareMatrix2 m4 = new SquareMatrix2(new double[]{2,1,1,2});
		System.out.println("m2=");
		System.out.println(m2);
		System.out.println("m3=");
		System.out.println(m3);
		System.out.println("m4=");
		System.out.println(m4);
		System.out.println("m2*m3=");
		final SquareMatrix2 m5 = m2.multiply(m3);
		System.out.println(m5);
		System.out.println("m2*m3*m4=");
		System.out.println(m5.multiply(m4));
	}
}