package ATPtest;

//文件分割：对清洗完毕的文件进行分割，以1000行为单位，输出至文件，将不足1000行的同样输出至另一个文件。
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class SplitFile {
	public static void split() throws Exception {
		long start = System.currentTimeMillis();
		String path = File.separator + "usr" + File.separator + "work" + File.separator + "dataclean" + File.separator;
		String[] ff = dataclean.listfiles(path);
		for (int i = 0; i < ff.length; i++) {
			int number = countcolume.count(i);
			if (number > 1000) {
				SplitFile.start(i, 1000,
						File.separator + "usr" + File.separator + "work" + File.separator + "dataclean" + File.separator
								+ ff[i],
						File.separator + "usr" + File.separator + "work" + File.separator + "split" + File.separator
								+ "split" + ff[i]);

			}
			/*
			 * else { String line = null; BufferedReader br = null; br = new
			 * BufferedReader(new InputStreamReader(new
			 * FileInputStream(File.separator + "usr" + File.separator + "work"
			 * + File.separator + "dataclean" + File.separator + ff[i])));
			 * BufferedWriter bw = null; bw = new BufferedWriter(new
			 * FileWriter(File.separator + "usr" + File.separator + "work" +
			 * File.separator + "split" + File.separator + "split" + ff[i]));
			 * String line22 = null; while ((line22 = br.readLine()) != null) {
			 * bw.write(line22); bw.newLine(); bw.flush(); }
			 */
		}
		long end = System.currentTimeMillis();
		System.out.println("锟侥硷拷锟街革拷锟斤拷时锟斤拷" + (end - start) / 1000 + "锟诫！");
	}

	public static void start(int r, int rows, String sourceFilePath, String targetDirectoryPath) {
		File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetDirectoryPath);
		// 对源文件路径进行验证
		if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
			System.out.println("源锟侥硷拷锟斤拷锟斤拷锟节伙拷锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟斤拷锟斤拷锟�");
			return;
		}
		// 对目标文件进行验证
		if (targetFile.exists()) {
			if (!targetFile.isDirectory()) {
				System.out.println("目锟斤拷锟侥硷拷锟叫达拷锟斤拷,锟斤拷锟斤拷一锟斤拷锟侥硷拷锟斤拷");
				return;
			}
		} else {
			targetFile.mkdirs();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(sourceFile));
			BufferedWriter bw = null;
			String str = "";
			String path = File.separator + "usr" + File.separator + "work" + File.separator + "dataclean"
					+ File.separator;
			String[] fff = dataclean.listfiles(path);
			String tempData = br.readLine();
			int i = 1, s = 0;
			while (tempData != null) {
				str += tempData + "\r\n";
				if (i % rows == 0) {
					bw = new BufferedWriter(new FileWriter(new File(File.separator + "usr" + File.separator + "work"
							+ File.separator + "split" + File.separator + "split" + s + "_" + fff[r])));
					bw.write(str);
					bw.close();
					str = "";
					s += 1;
				}
				i++;
				tempData = br.readLine();
			}
			if ((i - 1) % rows != 0) {
				bw = new BufferedWriter(new FileWriter(new File(File.separator + "usr" + File.separator + "work"
						+ File.separator + "split" + File.separator + "split" + s + "_" + fff[r])));
				bw.write(str);
				bw.close();
				br.close();
				s += 1;
			}
			// System.out.println("锟侥硷拷锟街革拷锟斤拷锟�,锟斤拷锟街革拷锟斤拷锟�" + s + "锟斤拷锟侥硷拷");
		} catch (Exception e) {
		}

	}

}
