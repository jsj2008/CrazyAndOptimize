package com.crazy.chapter15.duplicate.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.crazy.chapter15.duplicate.Constants;

public class FileChannelTest {

	public static void main(String[] args) {
		File f = new File(Constants.basicPath + "nio/FileChannelTest.java");
		try (FileChannel inChannel = new FileInputStream(f).getChannel();
				FileChannel outChannel = new FileOutputStream(Constants.basicPath + "nio/FileChannelTest.txt").getChannel()) {
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			outChannel.write(buffer);
			buffer.clear();
			
			Charset charset = Charset.forName("GBK");
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buffer);
			System.out.println(charBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
