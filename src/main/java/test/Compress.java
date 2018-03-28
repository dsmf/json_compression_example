package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

import org.bson.BSONObject;
import org.bson.BasicBSONEncoder;

public class Compress {

	private static final Charset CHARSET = StandardCharsets.UTF_8;

	public static void main(String[] args) throws IOException, URISyntaxException {
		new Compress().run();
	}

	private void run() throws IOException, URISyntaxException {
		compressFile("exampleLatLongWithAttribs.json");
		compressFile("exampleLatLongAsArray.json");
	}

	private void compressFile(final String path) throws IOException, URISyntaxException {
		String content = readToString(path);
		System.out.println("\n\n" + path + ": \n\n" + content +"\n\n");
		
		System.out.println("JSON        \t" + content.getBytes(CHARSET).length);
		System.out.println("JSON + GZIP \t" + compress(content).getBytes(CHARSET).length);
		System.out.println("BSON        \t" + toBson(content).length);
		System.out.println("BSON + GZIP \t" + compress(new String(toBson(content), CHARSET)).getBytes(CHARSET).length);
	}

	@SuppressWarnings("deprecation")
	private byte[] toBson(String content) {
		BSONObject bson = (BSONObject) com.mongodb.util.JSON.parse(content);
		BasicBSONEncoder encoder = new BasicBSONEncoder();
		return encoder.encode(bson);
	}

	private String readToString(String filename) throws IOException, URISyntaxException {
		Path path = Paths.get(this.getClass().getClassLoader().getResource(filename).toURI());
		String content = new String(Files.readAllBytes(path));
		return content;
	}

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPOutputStream gzip = new GZIPOutputStream(out);) {
			gzip.write(str.getBytes(CHARSET));
			gzip.close();
			String outStr = out.toString(CHARSET.name());
			return outStr;
		}
	}
}
