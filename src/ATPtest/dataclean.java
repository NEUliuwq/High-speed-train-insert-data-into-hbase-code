package ATPtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class dataclean {
	
	public static void clean() throws Exception {
		long start = System.currentTimeMillis();
		BufferedReader br = null;
		String path = File.separator + "usr" + File.separator + "work" + File.separator + "csv" + File.separator;
		String[] f = listfiles(path);
		for (int i = 0; i < f.length; i++) {
			br = new BufferedReader(new FileReader(File.separator + "usr" + File.separator + "work" + File.separator
					+ "csv" + File.separator + (f[i].substring(0, f[i].length()))));
			BufferedWriter bw = null;
			bw = new BufferedWriter(new FileWriter(File.separator + "usr" + File.separator + "work" + File.separator
					+ "dataclean" + File.separator + ((f[i].substring(0, f[i].length() - 4)) + ".txt")));
			BufferedWriter bw2 = null;
			bw2 = new BufferedWriter(new FileWriter(File.separator + "usr" + File.separator + "work" + File.separator
					+ "baowen" + File.separator + ((f[i].substring(0, f[i].length() - 4)) + "baowen.txt")));
			BufferedWriter bw3 = null;
			bw3 = new BufferedWriter(new FileWriter(File.separator + "usr" + File.separator + "work" + File.separator
					+ "yichanglie" + File.separator + ((f[i].substring(0, f[i].length() - 4)) + "baowen.txt")));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] str = line.split(",");
				if (str.length<129) {
					bw3.write(line);
					bw3.newLine();// 锟斤拷锟斤拷 b
					bw3.flush();
                  //单独存放无效行（一开始报错了，应该存在列数不足129列，但暂时还不知道在哪个文件夹）
				}else if (str[7].equals("锟斤拷息锟斤拷锟斤拷锟斤拷")){
					//清除表头字段行
				}else if ((str[7].equals("0922H")) || (str[7].equals("0923H"))) {
				   //将报文信息单独存储
					bw2.write(line);
					bw2.newLine();// 锟斤拷锟斤拷 b
					bw2.flush();
				} else {
                  //将正常信息进行字符替换（将无效字符去除）并输出至文件
				  //注：其实这里可以不去除，因为是将所有数据当成字符型数据进行的处理，当进行数值型数据比较的时候才有必要去除以下字符。
					String line1 = line.replaceAll("H", "").replaceAll("km/h", "").replaceAll("m", "").replaceAll(",,",
							",");
					bw.write(line1);
					bw.newLine();// 锟斤拷锟斤拷
					bw.flush();
				}
			}

			br.close();
			bw.close();
			bw2.close();

		}
		long end = System.currentTimeMillis();
		System.out.println("锟斤拷锟斤拷锟斤拷洗锟斤拷时锟斤拷" + (end - start) / 1000 + "锟诫！");
	}

	public static String[] listfiles(String path) {
		File f = new File(path);
		String files[] = f.list();
		return files;
	}

}
