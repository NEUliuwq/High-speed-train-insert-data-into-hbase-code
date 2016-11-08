package ATPtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import version5.HBaseTestCase;

public class DataGather {

	public static final String openFileStyle = "r";

	public static final String fieldLimitChar = ",";

	public static final int fieldAllCount = 129;
	static int count1 = 0;
	int i = 0;

	// 按定义好的顺序加载分割好的文件，并对文件进行处理
	public void loadFile(int l) throws Exception {
		String line_record = null;
		int count = countcolume.splitcount(l); // 计算加载的文件的行数，根据行数决定要创建的数组的长度。
		String line[][] = new String[129][count]; // 创建数组接收读取的每一行的对应元素
		String[] str = listsplitfiles.listsplitfiles();
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(File.separator + "usr" + File.separator
				+ "work" + File.separator + "split" + File.separator + str[l])));
		int i = 0;
		while ((line_record = br.readLine()) != null) {
			String[] fields = null;
			fields = line_record.split(",");
			// 将读取的一行数据进行拆分，然后将每个元素赋给数组
			if (fields.length == fieldAllCount) {
				for (int p = 0; p < 129; p++) {
					line[p][i] = fields[p];

				}

			}
			i++;

		}
		// 调用压缩方法，对数据进行处理。
		for (int q = 0; q < 129; q++) {
			compare2(line[q]);
		}

	}

	// 对字符型数据进行压缩，将相邻字符型数据进行两两比较。
	public static void compare2(String[] line252) {

		String outputTT = null;
		String outputVV = null;
		String TT = null;
		String VV = null;

	//	System.out.print("{T[");

		for (int j = 0; j < line252.length - 1; j++) {
			boolean flag = (line252[j + 1]).equals(line252[j]);
			// 有可能会出现空指向，因为line252[]可能会为空，因此在dataclean类中将少于129列的过滤了。
			if (!flag) {

				outputTT = outputTT + "+" + (j + 1) + ",";

			}

		}
		if (outputTT == null) {
			TT = "+" + (line252.length - 1) + "],V[";
	//		System.out.print(TT);

		} else {
			TT = outputTT.substring(4, outputTT.length() - 1) + "],V[";
	//		System.out.print(TT);
		}

		for (int j = 0; j < line252.length - 1; j++) {
			boolean flag2 = (line252[j + 1]).equals(line252[j]);
			if (!flag2) {

				outputVV = outputVV  + line252[j + 1] + ",";
			}

		}
		if (outputVV == null) {
	//		System.out.println(line252[(line252.length - 1)] + "]}");

		} else {

	//		System.out.println(outputVV.substring(4, outputVV.length() - 1) + "]}");
		}

		// 将符合的T[]取出

		if (outputTT == null) {
			TT = "+" + (line252.length - 1);
		} else {
			TT = outputTT.substring(4, outputTT.length());
		}

		// 将符合的V[]取出

		if (outputVV == null) {
			VV =  line252[line252.length - 1];
		} else {
			VV = outputVV.substring(4, outputVV.length());
		}
		HBaseTestCase.fun(TT, VV);

	}

	// 对数值型数据进行压缩，对相邻数据两两比较，设定的波动范围设定的为3%。
	public void compare(double[] line130) {

		String outputT = null;
		String outputV = null;
		String T = null;
		String V = null;

		// System.out.print("{T[");

		for (int j = 0; j < line130.length - 1; j++) {
			if ((line130[j + 1]) > 1.03 * (line130[j]) || (line130[j + 1]) < 0.97 * (line130[j])) {

				outputT = outputT + "+" + (j + 1) + ",";

			}

		}
		if (outputT == null) {
			T = "+" + (line130.length - 1) + "],V[";
			// System.out.print(T);

		} else {
			T = outputT.substring(4, outputT.length() - 1) + "],V[";
			// System.out.print(T);
		}

		for (int j = 0; j < line130.length - 1; j++) {

			if ((line130[j + 1]) > 1.03 * (line130[j]) || (line130[j + 1]) < 0.97 * (line130[j])) {

				outputV = outputV + "+" + line130[j + 1] + ",";
			}

		}
		if (outputV == null) {
			// System.out.println("+" + line130[(line130.length - 1)] + "]}");

		} else {

			// System.out.println(outputV.substring(4, outputV.length() - 1) +
			// "]}");
		}

		//将符合的T[]取出

		if (outputT == null) {
			T = "+" + (line130.length - 1);
		} else {
			T = outputT.substring(4, outputT.length());
		}

		// 将符合的V[]取出

		if (outputV == null) {
			V = "+" + line130[line130.length - 1];
		} else {
			V = outputV.substring(4, outputV.length());
		}

		HBaseTestCase.fun(T, V);
	}

}
