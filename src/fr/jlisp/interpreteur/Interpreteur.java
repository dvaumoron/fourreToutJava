package fr.jlisp.interpreteur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.jlisp.interpreteur.runtime.Enhancers;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.EvalConsumer;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.interpreteur.runtime.builtins.FonctionAddMultiply;
import fr.jlisp.interpreteur.runtime.builtins.FonctionAll;
import fr.jlisp.interpreteur.runtime.builtins.FonctionAny;
import fr.jlisp.interpreteur.runtime.builtins.FonctionBitwise;
import fr.jlisp.interpreteur.runtime.builtins.FonctionBreakContinue;
import fr.jlisp.interpreteur.runtime.builtins.FonctionCallable;
import fr.jlisp.interpreteur.runtime.builtins.FonctionConcat;
import fr.jlisp.interpreteur.runtime.builtins.FonctionDict;
import fr.jlisp.interpreteur.runtime.builtins.FonctionDivide;
import fr.jlisp.interpreteur.runtime.builtins.FonctionDivideLong;
import fr.jlisp.interpreteur.runtime.builtins.FonctionGenVar;
import fr.jlisp.interpreteur.runtime.builtins.FonctionHashSet;
import fr.jlisp.interpreteur.runtime.builtins.FonctionId;
import fr.jlisp.interpreteur.runtime.builtins.FonctionInput;
import fr.jlisp.interpreteur.runtime.builtins.FonctionIsClass;
import fr.jlisp.interpreteur.runtime.builtins.FonctionIsInstance;
import fr.jlisp.interpreteur.runtime.builtins.FonctionJoin;
import fr.jlisp.interpreteur.runtime.builtins.FonctionList;
import fr.jlisp.interpreteur.runtime.builtins.FonctionMinus;
import fr.jlisp.interpreteur.runtime.builtins.FonctionNeg;
import fr.jlisp.interpreteur.runtime.builtins.FonctionNodeList;
import fr.jlisp.interpreteur.runtime.builtins.FonctionNodeValue;
import fr.jlisp.interpreteur.runtime.builtins.FonctionNodeœdentifier;
import fr.jlisp.interpreteur.runtime.builtins.FonctionNot;
import fr.jlisp.interpreteur.runtime.builtins.FonctionPower;
import fr.jlisp.interpreteur.runtime.builtins.FonctionPrint;
import fr.jlisp.interpreteur.runtime.builtins.FonctionRemainder;
import fr.jlisp.interpreteur.runtime.builtins.FonctionShift;
import fr.jlisp.interpreteur.runtime.builtins.FonctionShuffle;
import fr.jlisp.interpreteur.runtime.builtins.FonctionSort;
import fr.jlisp.interpreteur.runtime.builtins.FonctionSplit;
import fr.jlisp.interpreteur.runtime.builtins.FonctionThrow;
import fr.jlisp.interpreteur.runtime.builtins.FonctionType;
import fr.jlisp.interpreteur.runtime.builtins.MacroAnd;
import fr.jlisp.interpreteur.runtime.builtins.MacroBloc;
import fr.jlisp.interpreteur.runtime.builtins.MacroCall;
import fr.jlisp.interpreteur.runtime.builtins.MacroCallMethod;
import fr.jlisp.interpreteur.runtime.builtins.MacroCase;
import fr.jlisp.interpreteur.runtime.builtins.MacroClass;
import fr.jlisp.interpreteur.runtime.builtins.MacroComparator;
import fr.jlisp.interpreteur.runtime.builtins.MacroDecoratedClass;
import fr.jlisp.interpreteur.runtime.builtins.MacroDecoratedDef;
import fr.jlisp.interpreteur.runtime.builtins.MacroDef;
import fr.jlisp.interpreteur.runtime.builtins.MacroDel;
import fr.jlisp.interpreteur.runtime.builtins.MacroDelAttr;
import fr.jlisp.interpreteur.runtime.builtins.MacroEnhancer;
import fr.jlisp.interpreteur.runtime.builtins.MacroEquals;
import fr.jlisp.interpreteur.runtime.builtins.MacroEval;
import fr.jlisp.interpreteur.runtime.builtins.MacroFor;
import fr.jlisp.interpreteur.runtime.builtins.MacroForEach;
import fr.jlisp.interpreteur.runtime.builtins.MacroGetAttr;
import fr.jlisp.interpreteur.runtime.builtins.MacroGetEnhancers;
import fr.jlisp.interpreteur.runtime.builtins.MacroGetEnv;
import fr.jlisp.interpreteur.runtime.builtins.MacroGetModuleCache;
import fr.jlisp.interpreteur.runtime.builtins.MacroIf;
import fr.jlisp.interpreteur.runtime.builtins.MacroImport;
import fr.jlisp.interpreteur.runtime.builtins.MacroImportAll;
import fr.jlisp.interpreteur.runtime.builtins.MacroIs;
import fr.jlisp.interpreteur.runtime.builtins.MacroLambda;
import fr.jlisp.interpreteur.runtime.builtins.MacroOr;
import fr.jlisp.interpreteur.runtime.builtins.MacroQuote;
import fr.jlisp.interpreteur.runtime.builtins.MacroSet;
import fr.jlisp.interpreteur.runtime.builtins.MacroSetAttr;
import fr.jlisp.interpreteur.runtime.builtins.MacroSetMultiple;
import fr.jlisp.interpreteur.runtime.builtins.MacroTry;
import fr.jlisp.interpreteur.runtime.builtins.MacroWhile;
import fr.jlisp.interpreteur.runtime.builtins.MacroWith;
import fr.jlisp.lexical.AnalyseurLexical;
import fr.jlisp.syntaxique.AnalyseurSyntaxique;

public class Interpreteur {

	public static final AnalyseurLexical AL = AnalyseurLexical
			.createAnalyseur();

	public static void main(final String[] args) throws Exception {
		final Interpreteur interpreteur = new Interpreteur();
		interpreteur.evalModule(args[0], Arrays.asList(args));
	}

	private final Environement builtins = new Environement();
	private final Enhancers enhancers = new Enhancers();
	private final Map<String, Environement> moduleCache = new LinkedHashMap<>();

	public Interpreteur() throws IOException {
		this.builtins.set(Util.MODULE_NAME, Util.BUILTINS_NAME);
		this.builtins.set(Util.INTERPRETEUR_NAME, this);
		this.builtins.set(".", MacroCallMethod.CALL_METHOD);
		this.builtins.set("=", MacroEquals.EQUALS);
		this.builtins.set("<", MacroComparator.LESSER_THAN);
		this.builtins.set("<=", MacroComparator.LESSER_OR_EQUALS);
		this.builtins.set(">", MacroComparator.GREATER_THAN);
		this.builtins.set(">=", MacroComparator.GREATER_OR_EQUALS);
		this.builtins.set("+", FonctionAddMultiply.ADD);
		this.builtins.set("-", FonctionMinus.MINUS);
		this.builtins.set("*", FonctionAddMultiply.MULTIPLY);
		this.builtins.set("/", FonctionDivide.DIVIDE);
		this.builtins.set("//", FonctionDivideLong.DIVIDE_LONG);
		this.builtins.set("%", FonctionRemainder.REMAINDER);
		this.builtins.set("**", FonctionPower.POWER);
		this.builtins.set("&", FonctionBitwise.AND);
		this.builtins.set("|", FonctionBitwise.OR);
		this.builtins.set("^", FonctionBitwise.XOR);
		this.builtins.set("<<", FonctionShift.LEFT);
		this.builtins.set(">>", FonctionShift.RIGHT);
		this.builtins.set("@class", MacroDecoratedClass.DECORATED_CLASS);
		this.builtins.set("@def", MacroDecoratedDef.DECORATED_DEF);
		this.builtins.set("all", FonctionAll.ALL);
		this.builtins.set("and", MacroAnd.AND);
		this.builtins.set("any", FonctionAny.ANY);
		this.builtins.set("bloc", MacroBloc.BLOC);
		this.builtins.set("break", FonctionBreakContinue.BREAK);
		this.builtins.set("call", MacroCall.CALL);
		this.builtins.set("callable", FonctionCallable.CALLABLE);
		this.builtins.set("case", MacroCase.CASE);
		this.builtins.set(Util.CLASS, MacroClass.CLASS);
		this.builtins.set("continue", FonctionBreakContinue.CONTINUE);
		this.builtins.set("def", MacroDef.DEF);
		this.builtins.set("defMacro", MacroDef.DEF_MACRO);
		this.builtins.set("del", MacroDel.DEL);
		this.builtins.set("delAttr", MacroDelAttr.DEL_ATTR);
		this.builtins.set("dict", FonctionDict.DICT);
		this.builtins.set("enhancer", MacroEnhancer.ENHANCER);
		this.builtins.set("eval", MacroEval.EVAL);
		this.builtins.set("for", MacroFor.FOR);
		this.builtins.set("forEach", MacroForEach.FOR_EACH);
		this.builtins.set("genVar", FonctionGenVar.GEN_VAR);
		this.builtins.set("getAttr", MacroGetAttr.GET_ATTR);
		this.builtins.set("getEnhancers", MacroGetEnhancers.GET_ENHANCERS);
		this.builtins.set("getEnv", MacroGetEnv.GET_ENV);
		this.builtins.set("getModuleCache",
				MacroGetModuleCache.GET_MODULE_CACHE);
		this.builtins.set("hashSet", FonctionHashSet.HASH_SET);
		this.builtins.set("id", FonctionId.ID);
		this.builtins.set("if", MacroIf.IF);
		this.builtins.set("importAll", MacroImportAll.IMPORT_ALL);
		this.builtins.set("importJava", MacroImport.IMPORT_JAVA);
		this.builtins.set("importJLisp", MacroImport.IMPORT_JLISP);
		this.builtins.set("input", FonctionInput.INPUT);
		this.builtins.set("is", MacroIs.IS);
		this.builtins.set("isClass", FonctionIsClass.IS_CLASS);
		this.builtins.set("isInstance", FonctionIsInstance.IS_INSTANCE);
		this.builtins.set(Util.LAMBDA, MacroLambda.LAMBDA);
		this.builtins.set("list", FonctionList.LIST);
		this.builtins.set("neg", FonctionNeg.NEG);
		this.builtins.set("nodeIdentifier",
				FonctionNodeœdentifier.NODE_IDENTIFIER);
		this.builtins.set("nodeList", FonctionNodeList.NODE_LIST);
		this.builtins.set("nodeValue", FonctionNodeValue.NODE_VALUE);
		this.builtins.set("not", FonctionNot.NOT);
		this.builtins.set("or", MacroOr.OR);
		this.builtins.set("print", FonctionPrint.PRINT);
		this.builtins.set("quote", MacroQuote.QUOTE);
		this.builtins.set("set", MacroSet.SET);
		this.builtins.set("setAttr", MacroSetAttr.SET_ATTR);
		this.builtins.set("setM", MacroSetMultiple.SET_MULTIPLE);
		this.builtins.set("shuffle", FonctionShuffle.SHUFFLE);
		this.builtins.set("sort", FonctionSort.SORT);
		this.builtins.set("throw", FonctionThrow.THROW);
		this.builtins.set("try", MacroTry.TRY);
		this.builtins.set("type", FonctionType.TYPE);
		this.builtins.set(Util.UNQUOTE, FonctionId.ID);
		this.builtins.set("while", MacroWhile.WHILE);
		this.builtins.set("with", MacroWith.WITH);

		this.builtins.set("Character", Character.class);
		this.builtins.set("Double", Double.class);
		this.builtins.set("Exception", Exception.class);
		this.builtins.set("Long", Long.class);
		this.builtins.set("String", String.class);

		this.enhancers.put(String.class, "join", FonctionJoin.JOIN);
		this.enhancers.put(String.class, "split", FonctionSplit.SPLIT);
		this.enhancers.put(String.class, Util.APPLY, FonctionConcat.CONCAT);

		final Path path = Paths.get("scriptSrc/builtins.jlisp");
		this.evalFile(path, this.builtins);
		this.moduleCache.put(Util.BUILTINS_NAME, this.builtins);
	}

	public Object eval(final String phrase, final Environement environement)
			throws Exception {
		return AnalyseurSyntaxique.parse(AL.parse(phrase)).eval(environement);
	}

	public Environement evalModule(final String moduleName) throws IOException {
		return this.evalModule(moduleName, null);
	}

	public Environement evalModule(final String moduleName,
			final List<String> args) throws IOException {
		if (this.moduleCache.containsKey(moduleName)) {
			return this.moduleCache.get(moduleName);
		} else {
			final String filePath = moduleName.replaceAll("\\.", "/").concat(
					".jlisp");
			final Path path = Paths.get("scriptSrc", filePath);
			final Environement fileEnv = new EnvironementLocal(this.builtins);
			if (args == null) {
				fileEnv.set(Util.MODULE_NAME, moduleName);
			} else {
				fileEnv.set(Util.MODULE_NAME, "_main_");
				fileEnv.set("args", args);
			}
			this.moduleCache.put(moduleName, fileEnv);

			this.evalFile(path, fileEnv);

			return fileEnv;
		}
	}

	private void evalFile(final Path path, final Environement environement)
			throws IOException {
		final EvalConsumer evalConsumer = new EvalConsumer(this, environement);
		Files.lines(path).forEach(evalConsumer);
		if (!evalConsumer.isEmpty()) {
			throw new IllegalStateException("code non executÈ");
		}
	}

	public Environement getBuiltins() {
		return this.builtins;
	}

	public Enhancers getEnhancers() {
		return this.enhancers;
	}

	public Map<String, Environement> getModuleCache() {
		return this.moduleCache;
	}
}