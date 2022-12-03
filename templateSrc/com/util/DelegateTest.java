<%= packageStmt =%>

import java.util.ArrayList;
import java.util.List;

public class <%= name =%> {

	private List<Object> delegate = new ArrayList<>();
	private int size = 0;

	<%= util.delegate(1, "java.util.List", "delegate", "add", "clear", "contains") =%>
	<%= util.equals(1, name, "java.util.List", "delegate", "int", "size") =%>
	<%= util.hashCode(1, "java.util.List", "delegate", "int", "size") =%>
	<%= util.toString(1, name, "delegate", "size") =%>
}