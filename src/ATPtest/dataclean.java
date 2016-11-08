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
					bw3.newLine();// ���� b
					bw3.flush();
                  //���������Ч�У�һ��ʼ�����ˣ�Ӧ�ô�����������129�У�����ʱ����֪�����ĸ��ļ��У�
				}else if (str[7].equals("��Ϣ������")){
					//�����ͷ�ֶ���
				}else if ((str[7].equals("0922H")) || (str[7].equals("0923H"))) {
				   //��������Ϣ�����洢
					bw2.write(line);
					bw2.newLine();// ���� b
					bw2.flush();
				} else {
                  //��������Ϣ�����ַ��滻������Ч�ַ�ȥ������������ļ�
				  //ע����ʵ������Բ�ȥ������Ϊ�ǽ��������ݵ����ַ������ݽ��еĴ�����������ֵ�����ݱȽϵ�ʱ����б�Ҫȥ�������ַ���
					String line1 = line.replaceAll("H", "").replaceAll("km/h", "").replaceAll("m", "").replaceAll(",,",
							",");
					bw.write(line1);
					bw.newLine();// ����
					bw.flush();
				}
			}

			br.close();
			bw.close();
			bw2.close();

		}
		long end = System.currentTimeMillis();
		System.out.println("������ϴ��ʱ��" + (end - start) / 1000 + "�룡");
	}

	public static String[] listfiles(String path) {
		File f = new File(path);
		String files[] = f.list();
		return files;
	}

}
