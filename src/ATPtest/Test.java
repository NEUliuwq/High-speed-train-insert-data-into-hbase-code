package ATPtest;

/*
 *  �ĳ��������ATP���ݣ�û��ѹ���Ĵ��롣
ע�����л���Ϊ��Ⱥ���ڵ�Master��eclipse�ϡ�
 �����ڶ�ӦĿ¼�´�����Ӧ���ļ��У�����Ȩ��
��/usr/work/csv���´�Ŵ������csvԭʼ�ļ�
��/usr/work/dataclean���´����ϴ����������ݣ�0921H��
��/usr/work/baowen���´����ϴ��ı������ݣ�0922H��0923H��
��/usr/work/yichanglie���´����������129�е��쳣����
��/usr/work/split���´�Ű���ѹ����ʽ��Ƶķָ��ļ����������յ���Ч�ļ� 
*/
public class Test {

	public static void main(String[] args) {

		try {
			Long start = System.currentTimeMillis();
			dataclean.clean(); // ������ϴ
			SplitFile.split(); // ���ݷָ�
			new HBaseTestCase("test93", "{T[], V[]}").create(); // ����HBase���
			DataGather gather = new DataGather();
			String[] str = listsplitfiles.listsplitfiles(); // �����ָ��Ŀ¼�µ��ļ�
			for (int i = 0; i < str.length; i++) {

				gather.loadFile(i); // ѭ�����ļ����д���
			}
			Long end = System.currentTimeMillis();
			System.out.println("总共用时" + ((end - start) / 1000) + "秒！");
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
