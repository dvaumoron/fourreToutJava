<%= packageStmt =%>

public class <%= name =%> implements ABVisitable {

	<%= util.acceptVisitor(1, name, "ABVisitor", "int") =%>
}