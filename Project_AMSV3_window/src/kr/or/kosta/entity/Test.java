package kr.or.kosta.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Test {
	public static void main(String[] args) throws IOException {
		String path ="test.txt";
		String accountNum ="1111-2222-3333-4444";
		RandomAccessFile raf = new RandomAccessFile(path, "rw"); 
		char[] text = new char[accountNum.length()];
		text = accountNum.toCharArray();
		for (char c : text) {
			raf.write(c);
		}
		System.out.println(raf.getFilePointer());

		
	}
}
