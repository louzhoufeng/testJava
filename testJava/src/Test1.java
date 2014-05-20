public class Test1 {
	public static void main(String[] args) {
		
		
//		double[] point = {60,40};
//		double[] polyline = {60,5,120,5,85,60,25,60};
		double[] point = {7,7};
		double[] polyline = {5,5,40,5,5,60};
		int i = Test1.PolygonIsContainPoint(point, polyline);
		System.out.println(i);
		
		byte bt = -64 ;
		int info = (bt & 0xff) ;
		System.out.println("-64 is "+info);
		
		final int intNmu = 5 ;
		
		Test1 t1 = new Test1();
		t1.prin(9);
		
		int size = 3 ;
		System.out.println(-8%4); 
		System.out.println(-7%4); 
		System.out.println(-6%4);
		System.out.println(-5%4);
		System.out.println(-4%4);
		System.out.println(-3%4);
		System.out.println(-2%4);
		System.out.println(-1%4);
		System.out.println(0%4); 
		System.out.println(8%4); 
		System.out.println(7%4); 
		System.out.println(6%4);
		System.out.println(5%4);
		System.out.println(4%4);
		System.out.println(3%4);
		System.out.println(2%4);
		System.out.println(1%4);
		System.out.println(0%4); 
	}

	private void prin(int num){
		System.out.println(num);
		
	}

	// <summary>
	// / <para>�жϵ��Ƿ��ڶ���εķ�Χ��</para>
	// / <para>����ֵ��ֵΪ1��ʾ���ڶ���η�Χ�ڣ�</para>
	// / <para>ֵΪ0��ʾ���ڶ���α��ϣ�</para>
	// / <para>ֵΪ-1��ʾ�㲻�ڶ���η�Χ�ڡ�</para>
	// / </summary>
	// / <param name="point">�����꣬����Ϊ2</param>
	// / <param name="polyline">����νڵ����꣬����Ϊ2*n������nӦ���ڻ����3��������Ϊ������</param>
	// / <returns>
	// / <para>����ֵ��ֵΪ1��ʾ���ڶ���η�Χ�ڣ�</para>
	// / <para>ֵΪ0��ʾ���ڶ���α��ϣ�</para>
	// / <para>ֵΪ-1��ʾ�㲻�ڶ���η�Χ�ڡ�</para>
	// / </returns>
	public static int PolygonIsContainPoint(double[] point, double[] polyline) {
		int result = -1, count = 0, pointcount = 0, tempI;
		double maxx = 0, minx = 0, maxy = 0, miny = 0;
		if (polyline != null) {
			int i;
			pointcount = polyline.length / 2;
			maxx = minx = polyline[0];
			maxy = miny = polyline[1];
			for (i = 0; i < pointcount; i++) {
				tempI = i + i;
				if (maxx < polyline[tempI])
					maxx = polyline[tempI];
				if (minx > polyline[tempI])
					minx = polyline[tempI];
				if (maxy < polyline[tempI + 1])
					maxy = polyline[tempI + 1];
				if (miny > polyline[tempI + 1])
					miny = polyline[tempI + 1];
			}
		}
		if (point != null) {

			// �����ж��Ƿ���������Χ��
			if (point[0] < minx || point[0] > maxx || point[1] < miny
					|| point[1] > maxy) {
				return result;
			} else {
				int i, j;
				j = pointcount - 1;
				double[] point1, point2;
				double tempValue;
				for (i = 0; i < pointcount; i++) {
					point1 = new double[2];
					point2 = new double[2];
					tempI = i + i;
					point1[0] = polyline[tempI];
					point1[1] = polyline[tempI + 1];
					tempI = j + j;
					point2[0] = polyline[tempI];
					point2[1] = polyline[tempI + 1];
					if ((point1[0] < point[0] && point2[0] >= point[0])
							|| (point2[0] < point[0] && point1[0] >= point[0])) {
						tempValue = point1[1] + (point[0] - point1[0])
								/ (point2[0] - point1[0])
								* (point2[1] - point1[1]);
						if (tempValue < point[1]) {
							count++;
						} else if (tempValue == point[1]) {
							count = -1;
							break;
						}
					}
					j = i;
				}
			}
		}
		if (count == -1) {
			result = 0;// �����߶���
		} else {
			tempI = count % 2;
			if (tempI == 0)// Ϊż��
			{
				result = -1;
			} else {
				result = 1;
			}
		}
		return result;
	}

}
