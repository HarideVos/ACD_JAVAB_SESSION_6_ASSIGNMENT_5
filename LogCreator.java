package sixpointfive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class LogWriter {
	File f;
	BufferedWriter br;
	Date timestamp = new Date();
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss");

	LogWriter() {
		f = new File("log.txt");

		if (f.exists()) {
			System.out.println("log." + dateFormat.format(timestamp) + ".txt");
			File f2 = new File("log." + dateFormat.format(timestamp) + ".txt");
			f.renameTo(f2);

		}

		try {
			f.createNewFile();
			System.out.println("File Created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void writeToFile(String text) throws FileTooLargeException, ErrorWritingToFile {
		if (f.length() > 5242880)
			throw new FileTooLargeException("Cannot Write, file too large");
		else
			try {
				br = new BufferedWriter(new FileWriter(f, true));
				br.write(dateFormat.format(timestamp) + " : NO TYPE : " + text);
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	void writeToFile(String textType, String text) throws FileTooLargeException, ErrorWritingToFile {
		if (f.length() > 5242880)
			throw new FileTooLargeException("Cannot Write, file too large");
		else
			try {
				br = new BufferedWriter(new FileWriter(f, true));
				br.write(dateFormat.format(timestamp) + " : " + textType + " : " + text);
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}

class FileTooLargeException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5953223426867906681L;

	/**
	 * 
	 */


	public FileTooLargeException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}

class ErrorWritingToFile extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5341230587303539946L;

	/**
	 * 
	 */

	public ErrorWritingToFile(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}

public class LogCreator {

	public static void main(String[] args) {
		LogWriter lw = new LogWriter();
		String n;
		for (int i = 0; i < 20; i++) {
			n = String.valueOf(Math.random());
			try {
				lw.writeToFile("Error", n + "\n");
			} catch (FileTooLargeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			} catch (ErrorWritingToFile e) {
				// TODO Auto-generated catch block
				System.out.println("Oops.");
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 200000; i++) {
			n = String.valueOf(Math.random());
			try {
				lw.writeToFile(n + "\n");
			} catch (FileTooLargeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			} catch (ErrorWritingToFile e) {
				// TODO Auto-generated catch block
				System.out.println("Oops.");
				e.printStackTrace();
			}
		}
		System.out.println("Done");

	}

}
