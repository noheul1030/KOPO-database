package kopo11.basicTraining2;

import java.io.*;
import java.util.*;

public class GradeList {
	public static int random() {
		return new Random().nextInt(101);
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter Writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("resource/gradeSave.csv"), "UTF-8"));

		String headData = "�й�,�̸�,����,����,����";
		Writer.write(headData);
		Writer.write(System.lineSeparator());

		String name = "ȫ�浿";
		int count = 0;
		while (count < 1000) {
			if(count <10) {
				Writer.write(count + "," + "ȫ�浿0"+count + "," + random() + "," + random() + "," + random());
				Writer.write(System.lineSeparator());
				count++;
			}
			else {
				Writer.write(count + "," + name+count + "," + random() + "," + random() + "," + random());
				Writer.write(System.lineSeparator());
				count++;
			}
		}
		Writer.flush();
		Writer.close();

	}
}
