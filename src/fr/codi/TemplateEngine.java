package fr.codi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import fr.codi.text.Rope;

public class TemplateEngine {

	public static void main(String[] args) {

		final long start = System.currentTimeMillis();

		File srcFolder = new File(args[0]);
		File destFolder = new File(args[1]);

		if (srcFolder.isDirectory() && destFolder.isDirectory()) {
			TemplateEngine engine = new TemplateEngine(false);

			engine.computeFolder(srcFolder, destFolder, "");
		}

		final long end = System.currentTimeMillis();

		System.out.println();
		System.out.print("temps de calcul : ");
		System.out.print(end - start);
		System.out.println(" milliSecondes");
	}

	private final ScriptEngineManager sem = new ScriptEngineManager();
	private final boolean debug;

	public TemplateEngine(boolean debug) {
		this.debug = debug;
	}

	public void computeFolder(final File srcFolder, final File destFolder,
			final String packageName) {

		System.out.print("srcFolder = ");
		System.out.println(srcFolder);
		System.out.print("destFolder = ");
		System.out.println(destFolder);
		System.out.print("package = ");
		System.out.println(packageName);

		for (final File file : srcFolder.listFiles()) {
			final String fileName = file.getName();
			Rope destBuffer = new Rope(3);
			destBuffer.append(destFolder.getPath());
			destBuffer.append(File.separator);
			destBuffer.append(fileName);
			final File destFile = new File(destBuffer.toString());
			destBuffer = null;
			if (file.isDirectory()) {
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				Rope packageBuffer = new Rope(3);
				if (!(packageName == null || packageName.isEmpty())) {
					packageBuffer.append(packageName);
					packageBuffer.append(".");
				}
				packageBuffer.append(fileName);
				this.computeFolder(file, destFile, packageBuffer.toString());
			} else {
				this.computeFile(file, destFile, packageName, fileName);
			}
		}
	}

	private void computeFile(final File file, final File destFile,
			final String packageName, final String fileName) {

		System.out.print("file : ");
		System.out.println(fileName);

		try (final FileReader fr = new FileReader(file);
				final BufferedReader reader = new BufferedReader(fr);
				final FileWriter fw = new FileWriter(destFile);
				final BufferedWriter writer = new BufferedWriter(fw)) {
			final WriterAdapter out = new WriterAdapter(writer);

			Rope packageStmtBuffer = new Rope(3);
			if (!(packageName == null || packageName.isEmpty())) {
				packageStmtBuffer.append("package ");
				packageStmtBuffer.append(packageName);
				packageStmtBuffer.append(";");
			}
			final String packageStmt = packageStmtBuffer.toString();
			packageStmtBuffer = null;

			final String name = fileName.substring(0, fileName.indexOf("."));

			final ScriptEngine se = this.sem.getEngineByExtension("js");
			se.put("packageStmt", packageStmt);
			se.put("name", name);
			se.put("util", Util.INSTANCE);
			se.put("out", out);

			Rope scriptBuffer = new Rope();

			String line;
			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("\\'", "\\\\'");
				line = line.replaceAll("<%=", "');\n out.write(");
				line = line.replaceAll("<%", "');\n");
				line = line.replaceAll("=%>", ");\n out.write('");
				line = line.replaceAll("%>", "\n out.write('");

				scriptBuffer.append("out.write('");
				scriptBuffer.append(line);
				scriptBuffer.append("\\n');\n");
			}

			final String script = scriptBuffer.toString();
			if (this.debug) {
				System.out.println(script);
			}
			se.eval(script);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
