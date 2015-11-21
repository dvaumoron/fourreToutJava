package org.codi;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LispInterpreter {

	private Environnement environnement = new Environnement();

	public static void main(String[] args) {
		LispInterpreter interpreter = new LispInterpreter();
		interpreter.init();
		interpreter.eval("( def fact ( n ) ( if ( = 0 n ) 1 ( * n ( fact (- n 1 ) ) ) ) )");
		interpreter.eval("( print ( fact 5 ) )");
	}

	public void init() {
		environnement.put("set", Set.INSTANCE);
		environnement.put("quote", Quote.INSTANCE);
		environnement.put("unquote", Unquote.INSTANCE);
		environnement.put("lambda", Lambda.LAMBDA_INSTANCE);
		environnement.put("macro", Lambda.MACRO_INSTANCE);
		environnement.put("if", If.INSTANCE);
		environnement.put("print", Print.INSTANCE);
		environnement.put("=", Equal.INSTANCE);
		environnement.put("+", Plus.INSTANCE);
		environnement.put("-", Moins.INSTANCE);
		environnement.put("*", Fois.INSTANCE);
		environnement.put("/", Sur.INSTANCE);		
		eval("( set defmacro ( macro ( name arg body ) ' ( set , name  ( macro , arg , body ) ) ) )");
		eval("( defmacro def ( name arg body ) ' ( set , name  ( lambda , arg , body ) ) )");
	}

	public LispObject eval(String program) {
		return parse(split(program)).eval(environnement);
	}

	public static LinkedList<String> split(String program) {
		// TODO parser lexical
		return new LinkedList<String>(Arrays.asList(program.split(" ")));
	}

	public static LispObject parse(Deque<String> tokens) {
		// TODO parsing syntaxique
		if (tokens.isEmpty()) {
			return EmptyList.INSTANCE;
		}
		String token = tokens.pop();
		if ("(".equals(token)) {
			LispListBuilder builder = new LispListBuilder();
			while (!")".equals(tokens.peek())) {
				if (".".equals(tokens.peek())) {
					tokens.pop();
					builder.addAsTail(parse(tokens));
				} else {
					builder.add(parse(tokens));
				}
			}
			tokens.pop();
			return builder.build();
		} else if ("'".equals(token)) {
			LispListBuilder builder = new LispListBuilder();
			builder.add(Quote.INSTANCE);
			builder.add(parse(tokens));
			return builder.build();
		} else if (",".equals(token)) {
			LispListBuilder builder = new LispListBuilder();
			builder.add(Unquote.INSTANCE);
			builder.add(parse(tokens));
			return builder.build();
		}{
			return convert(token);
		}
	}

	public static LispObject convert(String atom) {
		// get type from parser lexical
		LispObject res;
		if ("true".equalsIgnoreCase(atom)) {
			res = new Value(true);
		} else if ("false".equalsIgnoreCase(atom)) {
			res = new Value(false);
		} else if (atom.startsWith("\"")) {
			res = new Value(atom.substring(1, atom.length() - 1));
		} else {
			try {
				res = new Value(Long.parseLong(atom));
			} catch (NumberFormatException nfe) {
				try {
					res = new Value(Double.parseDouble(atom));
				} catch (NumberFormatException nfe2) {
					res = new Symbol(atom);
				}
			}
		}
		return res;
	}
}

class LispListBuilder {
	private LispObject head = EmptyList.INSTANCE;
	private LispObject lastPaire = null;

	public void add(LispObject lispObject) {
		Paire paire = new Paire();
		paire.head = lispObject;
		if (head == EmptyList.INSTANCE) {
			head = paire;
			lastPaire = paire;
		} else {
			((Paire) lastPaire).tail = paire;
			lastPaire = paire;
		}
	}

	public void addAsTail(LispObject lispObject) {
		((Paire) lastPaire).tail = lispObject;
		lastPaire = null;
	}
	
	public LispObject build() {
		if (lastPaire != null) {
			((Paire) lastPaire).tail = EmptyList.INSTANCE;
		}
		return head;
	}
}

interface LispObject {
	public LispObject eval(Environnement environnement);
}

class Value implements LispObject {
	
	public Object value;

	public Value(Object value) {
		this.value = value;
	}

	@Override
	public LispObject eval(Environnement environnement) {
		return this;
	}

	public String toString() {
		return value.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Value && ((Value) obj).value.equals(value);
	}

}

class Symbol implements LispObject {
	
	public String name;

	public Symbol(String name) {
		this.name = name;
	}

	@Override
	public LispObject eval(Environnement environnement) {
		return environnement.get(this.name);
	}

	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Symbol && ((Symbol) obj).name.equals(name);
	}

}

abstract class Function implements LispObject {

	private static final Symbol	SYMBOL_UNQUOTE = new Symbol("unquote");

	protected LispObject argSpec;

	@Override
	public LispObject eval(Environnement environnement) {
		return this;
	}
	
	public abstract LispObject eval(LispObject arg, Environnement environnement);

	private static LispObject evalList(LispObject lispObject, Environnement environnement) {
		if (lispObject instanceof Paire) {
			Paire paire = (Paire) lispObject;
			Paire paire2 = new Paire();
			paire2.head = paire.head.eval(environnement);
			paire2.tail = evalList(paire.tail, environnement);
			return paire2;
		} else {
			return lispObject.eval(environnement);
		}
	}

	private static LispObject evalListOnlyUnquote(LispObject lispObject, Environnement environnement) {
		if (lispObject instanceof Paire) {
			Paire paire = (Paire) lispObject;
			Paire paire2 = new Paire();
			LispObject eval = paire.head;
			if (eval == Unquote.INSTANCE || eval.equals(SYMBOL_UNQUOTE)) {
				paire2.head = eval.eval(environnement);
				paire2.tail = evalList(paire.tail, environnement);
			} else {
				paire2.head = paire.head;
				paire2.tail = evalListOnlyUnquote(paire.tail, environnement);
			}
			return paire2;
		} else {
			return lispObject;
		}
	}
	
	public static void match(LispObject arg, LispObject argSpec, Environnement environnement) {
		if (argSpec != EmptyList.INSTANCE) {
			if (argSpec instanceof Symbol) {
				Symbol symbol = (Symbol) argSpec;
				environnement.put(symbol.name, evalList(arg, environnement));
			} else if (argSpec instanceof Paire) {
				Paire paireSpec = (Paire) argSpec;
				Paire paire = (Paire) arg;
				match(paire.head, paireSpec.head, environnement);
				match(paire.tail, paireSpec.tail, environnement);
			}
		}
	}

	public static void matchWithoutEval(LispObject arg, LispObject argSpec, Environnement environnement) {
		if (argSpec != EmptyList.INSTANCE) {
			if (argSpec instanceof Symbol) {
				Symbol symbol = (Symbol) argSpec;
				environnement.put(symbol.name, evalListOnlyUnquote(arg, environnement));
			} else if (argSpec instanceof Paire) {
				Paire paireSpec = (Paire) argSpec;
				Paire paire = (Paire) arg;
				matchWithoutEval(paire.head, paireSpec.head, environnement);
				matchWithoutEval(paire.tail, paireSpec.tail, environnement);
			}
		}
	}
}

class UserDefinedFunction extends Function {

	private boolean macro;
	private LispObject body;

	public UserDefinedFunction(boolean macro, LispObject argSpec, LispObject body) {
		this.macro = macro;
		this.argSpec = argSpec;
		this.body = body;
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local  = new EnvironnementLocal(environnement);

		if (macro) {
			matchWithoutEval(arg, argSpec, local);
		} else {
			match(arg, argSpec, local);
		}

		LispObject res = body.eval(local);
		if (macro) {
			res = res.eval(environnement);
		}
		return res;
	}
}

class Paire implements LispObject {
	public LispObject head;
	public LispObject tail;

	@Override
	public LispObject eval(Environnement environnement) {
		Function function = (Function) head.eval(environnement);
		return function.eval(tail, environnement);
	}

	@Override
	public String toString() {
		return "(" + head + " . " + tail + ")";
	}

}

class EmptyList implements LispObject {

	public static final EmptyList INSTANCE = new EmptyList();
	
	private EmptyList() {
	}

	@Override
	public LispObject eval(Environnement environnement) {
		return this;
	}

	@Override
	public String toString() {
		return "()";
	}

}

class Environnement {

	private Map<String, LispObject> variables = new HashMap<String, LispObject>();

	public LispObject get(String name) {
		return variables.get(name);
	}

	public LispObject put(String key, LispObject value) {
		return variables.put(key, value);
	}

}

class EnvironnementLocal extends Environnement {

	private Environnement surEnvironnement;

	public EnvironnementLocal(Environnement surEnvironnement) {
		this.surEnvironnement = surEnvironnement;
	}

	public LispObject get(String name) {
		LispObject res = super.get(name);
		if (res == null) {
			res = surEnvironnement.get(name);
		}
		return res;
	}

}

class Set extends Function {

	public static final Set INSTANCE = new Set();

	private Set() {
		LispListBuilder builder = new LispListBuilder();
		builder.add(new Symbol("var"));
		builder.add(new Symbol("value"));
		this.argSpec = builder.build();
	}
	
	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local  = new EnvironnementLocal(environnement);

		matchWithoutEval(arg, argSpec, local);

		Symbol var = (Symbol) local.get("var");
		
		LispObject value = local.get("value").eval(environnement);
		
		environnement.put(var.name, value);
		
		return value;
	}

}

class Quote extends Function {
	
	public static final Quote INSTANCE = new Quote();

	private Quote() {
		LispListBuilder builder = new LispListBuilder();
		builder.add(new Symbol("toQuote"));
		this.argSpec = builder.build();
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local  = new EnvironnementLocal(environnement);

		matchWithoutEval(arg, argSpec, local);

		return local.get("toQuote");
	}

}

class Unquote extends Function {
	
	public static final Unquote INSTANCE = new Unquote();

	private Unquote() {
		LispListBuilder builder = new LispListBuilder();
		builder.add(new Symbol("toUnquote"));
		this.argSpec = builder.build();
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local  = new EnvironnementLocal(environnement);

		match(arg, argSpec, local);

		return local.get("toUnquote");
	}

}

class Lambda extends Function {

	public static final Lambda LAMBDA_INSTANCE = new Lambda(false);
	public static final Lambda MACRO_INSTANCE = new Lambda(true);

	private boolean macro;
	
	private Lambda(boolean macro) {
		this.macro = macro;
		LispListBuilder builder = new LispListBuilder();
		builder.add(new Symbol("argSpec"));
		builder.add(new Symbol("body"));
		this.argSpec = builder.build();
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local  = new EnvironnementLocal(environnement);
		
		matchWithoutEval(arg, argSpec, local);
		
		return new UserDefinedFunction(macro, local.get("argSpec"), local.get("body"));
	}
	
}

class If extends Function {

	public static final If INSTANCE = new If();

	private If() {
		LispListBuilder builder = new LispListBuilder();
		builder.add(new Symbol("test"));
		builder.add(new Symbol("then"));
		builder.add(new Symbol("else"));
		this.argSpec = builder.build();
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local  = new EnvironnementLocal(environnement);

		matchWithoutEval(arg, argSpec, local);

		Value test = (Value) local.get("test");

		String nameBlocToEval;
		if ((Boolean) test.value) {
			nameBlocToEval = "then";
		} else {
			nameBlocToEval = "else";
		}
		return local.get(nameBlocToEval).eval(environnement); 
	}

}

class Print extends Function {

	public static final Print INSTANCE = new Print();

	private Print() {
		this.argSpec = new Symbol("toPrint");
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local = new EnvironnementLocal(environnement);

		match(arg, argSpec, local);

		LispObject lispObject = local.get("toPrint");
		while (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			System.out.print(paire.head);
			System.out.print(" ");
			lispObject =  paire.tail;
		}
		System.out.println();
		return EmptyList.INSTANCE;
	}

}

class Equal extends Function {

	public static final Equal INSTANCE = new Equal();

	private Equal () {
		LispListBuilder builder = new LispListBuilder();
		builder.add(new Symbol("o1"));
		builder.add(new Symbol("o2"));
		this.argSpec = builder.build();
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local = new EnvironnementLocal(environnement);

		match(arg, argSpec, local);
		
		LispObject o1 = local.get("o1");
		LispObject o2 = local.get("o2");

		return new Value(o1.equals(o2));
	}
}

class Plus extends Function {

	public static final Plus INSTANCE = new Plus();

	private Plus() {
		this.argSpec = new Symbol("toSum");
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local = new EnvironnementLocal(environnement);

		match(arg, argSpec, local);

		LispObject lispObject = local.get("toSum");

		double res = 0;
		while (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			res += ((Number) ((Value) paire.head).value).doubleValue();
			lispObject =  paire.tail;
		}
		return new Value(res);
	}

}

class Moins extends Function {

	public static final Moins INSTANCE = new Moins();

	private Moins() {
		this.argSpec = new Symbol("toSoustract");
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local = new EnvironnementLocal(environnement);
		
		match(arg, argSpec, local);

		LispObject lispObject = local.get("toSoustract");

		double res = 0;
		if (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			res = ((Number) ((Value) paire.head).value).doubleValue();
			lispObject =  paire.tail;
		}
		while (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			res -= ((Number) ((Value) paire.head).value).doubleValue();
			lispObject =  paire.tail;
		}
		return new Value(res);
	}

}

class Fois extends Function {

	public static final Fois INSTANCE = new Fois();

	private Fois() {
		this.argSpec = new Symbol("toMult");
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local = new EnvironnementLocal(environnement);
		
		match(arg, argSpec, local);

		LispObject lispObject = local.get("toMult");

		double res = 1;
		while (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			res *= ((Number) ((Value) paire.head).value).doubleValue();
			lispObject =  paire.tail;
		}
		return new Value(res);
	}

}

class Sur extends Function {

	public static final Sur INSTANCE = new Sur();

	private Sur() {
		this.argSpec = new Symbol("toDivide");
	}

	@Override
	public LispObject eval(LispObject arg, Environnement environnement) {
		Environnement local = new EnvironnementLocal(environnement);

		match(arg, argSpec, local);

		LispObject lispObject = local.get("toDivide");

		double res = 1;
		if (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			res = ((Number) ((Value) paire.head).value).doubleValue();
			lispObject =  paire.tail;
		}
		while (lispObject != EmptyList.INSTANCE) {
			Paire paire = (Paire) lispObject;
			res /= ((Number) ((Value) paire.head).value).doubleValue();
			lispObject =  paire.tail;
		}
		return new Value(res);
	}

}