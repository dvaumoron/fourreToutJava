package fr.codi;

import java.io.IOException;
import java.io.Writer;

public class WriterAdapter {

	private final Writer writer;

	public WriterAdapter(Writer writer) {
		this.writer = writer;
	}

	public void write(String string) throws IOException {
		this.writer.write(string);
	}
}
