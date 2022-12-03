<%= packageStmt =%>

import java.util.List;

public class <%= name =%> {

	public static void main(String[] args) {
		final ListMock2 mock = new ListMock2();
		final List l = new RevealAccessList(System.out, mock);
		l.add("toto");
		l.add(10, "titi");
		for (Object o : l) {
			System.out.println(o);
		}
		System.out.println(l.equals(null));
		System.out.println(l.equals(null));
		System.out.println(l.equals(null));
		try {
			l.remove(0);
		} catch (IllegalArgumentException iae) {
			System.out.println("catched IllegalArgumentException");
		}
		System.out.println(mock.getCalls());
	}
}