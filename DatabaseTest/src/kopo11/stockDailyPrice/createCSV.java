package kopo11.stockDailyPrice;

import java.io.*;

public class createCSV {

	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Users\\³ëÀ»\\Documents\\GitHub\\day_data\\THTSKS010H00.dat");
		BufferedReader br = new BufferedReader(new FileReader(f));

		BufferedWriter Writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("resource/StockDailyPrice.csv"), "UTF-8"));

		String readtxt;

		int cnt = 0;
		int wcnt = 0;
		while ((readtxt = br.readLine()) != null) {
			StringBuffer s = new StringBuffer();
			String[] field = readtxt.split("%_%");

			if (field.length > 2 && field[2].replace("^", "").trim().substring(0, 1).equals("A")) {
				s.append(field[0].replace("^", "").trim());
				for (int j = 1; j < field.length; j++) {
					s.append("," + field[j].replace("^", "").trim());
				}
				Writer.write(s.toString());
				Writer.newLine();
				wcnt++;
			}
			cnt++;
		}
		br.close();
		Writer.close();
		System.out.printf("Program End[%d][%d] records\n", cnt, wcnt);
	}
}



