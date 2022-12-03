<%= packageStmt =%>

import fr.codi.pattern.iterable.Range;

<% var mi = util.map(); %>
<% mi.put("add", "true") %>
<% mi.put("equals", util.list("true", "false", "true")) %>
<% mi.put("iterator", "new Range(10).iterator()") %>
<% mi.put("remove", util.throwException("IllegalArgumentException")) %>

<%= util.mock(name, "java.util.List", mi) =%>