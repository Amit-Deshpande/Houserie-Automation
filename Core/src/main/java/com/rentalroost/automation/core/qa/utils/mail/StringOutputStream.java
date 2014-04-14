package com.rentalroost.automation.core.qa.utils.mail;

import java.io.OutputStream;
import java.io.StringWriter;

/**
 * @author kaushik_vira
 *
 */
public class StringOutputStream extends OutputStream {

	// This buffer will contain the stream
	protected StringWriter buf = new StringWriter();

	public StringOutputStream() {
	}

	public void close() {
	}

	public void flush() {
		// Clear the buffer
		buf.flush();
	}

	public void write(byte[] b) {
		String str = new String(b);
		this.buf.append(str);
	}

	public void write(byte[] b, int off, int len) {
		String str = new String(b, off, len);
		this.buf.append(str);
	}

	public void write(int b) {
		String str = Integer.toString(b);
		this.buf.append(str);
	}

	public String toString() {
		return buf.toString();
	}
}
