package ATPtest;

/*
 *  改程序是针对ATP数据，没有压缩的代码。
注：运行环境为集群主节点Master的eclipse上。
 首先在对应目录下创建对应的文件夹，并授权限
“/usr/work/csv”下存放待处理的csv原始文件
“/usr/work/dataclean”下存放清洗后的正常数据（0921H）
“/usr/work/baowen”下存放清洗后的报文数据（0922H和0923H）
“/usr/work/yichanglie”下存放列数不足129列的异常数据
“/usr/work/split”下存放按照压缩方式设计的分割文件，即：最终的有效文件 
*/
public class Test {

	public static void main(String[] args) {

		try {
			Long start = System.currentTimeMillis();
			dataclean.clean(); // 数据清洗
			SplitFile.split(); // 数据分割
			new HBaseTestCase("test93", "{T[], V[]}").create(); // 创建HBase表格
			DataGather gather = new DataGather();
			String[] str = listsplitfiles.listsplitfiles(); // 遍历分割后目录下的文件
			for (int i = 0; i < str.length; i++) {

				gather.loadFile(i); // 循环对文件进行处理
			}
			Long end = System.currentTimeMillis();
			System.out.println("鎬诲叡鐢ㄦ椂" + ((end - start) / 1000) + "绉掞紒");
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
