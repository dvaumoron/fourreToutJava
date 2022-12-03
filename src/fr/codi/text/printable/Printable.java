package fr.codi.text.printable;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class Printable {

	protected Set<String> annotations = null;
	protected int modifiers = 0;
	protected List<String> genericTypes = null;

	public void addAnnotation(final String annotation) {
		if (this.annotations == null) {
			this.annotations = new LinkedHashSet<>();
		}
		this.annotations.add(annotation);
	}

	public void addModifier(final int modifier) {
		if ((this.modifiers & modifier) == 0) {
			if (modifier == Modifier.PUBLIC) {
				this.removeModifier(Modifier.PRIVATE);
				this.removeModifier(Modifier.PROTECTED);
			} else if (modifier == Modifier.PROTECTED) {
				this.removeModifier(Modifier.PRIVATE);
				this.removeModifier(Modifier.PUBLIC);
			} else if (modifier == Modifier.PRIVATE) {
				this.removeModifier(Modifier.PROTECTED);
				this.removeModifier(Modifier.PUBLIC);
			} else if (modifier == Modifier.ABSTRACT) {
				this.removeModifier(Modifier.FINAL);
			} else if (modifier == Modifier.FINAL) {
				this.removeModifier(Modifier.ABSTRACT);
			}
			this.modifiers += modifier;
		}
	}

	public void removeModifier(final int modifier) {
		if ((this.modifiers & modifier) != 0) {
			this.modifiers -= modifier;
		}
	}

	public void addGenericType(final String genericType) {
		if (this.genericTypes == null) {
			this.genericTypes = new ArrayList<>();
		}
		this.genericTypes.add(genericType);
	}

	protected void printGenericTypeTo(final Rope buffer) {
		if (!(this.genericTypes == null || this.genericTypes.isEmpty())) {
			buffer.append("<");
			final Iterator<String> it = this.genericTypes.iterator();
			buffer.append(it.next());
			while (it.hasNext()) {
				buffer.append(", ");
				buffer.append(it.next());
			}
			buffer.append(">");
		}
	}

	public Rope toRope(final Indenter indenter) {
		final Rope buffer = new Rope();
		buffer.append("\n");
		indenter.printIndentation(buffer);
		if (this.annotations != null) {
			for (final String annotation : this.annotations) {
				buffer.append("@");
				buffer.append(annotation);
				buffer.append("\n");
				indenter.printIndentation(buffer);
			}
		}
		if (this.modifiers != 0) {
			buffer.append(Modifier.toString(this.modifiers));
			buffer.append(" ");
		}
		return buffer;
	}

	public Rope toRope() {
		final Rope buffer = new Rope();
		if (this.annotations != null) {
			for (final String annotation : this.annotations) {
				buffer.append("@");
				buffer.append(annotation);
				buffer.append(" ");
			}
		}
		if (this.modifiers != 0) {
			buffer.append(Modifier.toString(this.modifiers));
			buffer.append(" ");
		}
		return buffer;
	}
}
