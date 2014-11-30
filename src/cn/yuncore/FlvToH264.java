package cn.yuncore;

import java.io.File;
import java.io.IOException;

/**
 * ¶ÁÈ¡FLVÎÄ¼þtag
 * 
 * @author Administrator
 * 
 */
public class FlvToH264 {

	public static void main(String[] args) throws IOException {
		int line = -1;
		FLVReader reader = new FLVReader(new File("test.flv"));
		if (args != null && args.length > 0) {
			reader = new FLVReader(new File(args[0]));
		}
		if (args != null && args.length > 1) {
			line = Integer.valueOf(args[1]);
		}
		final FLVFileHead flvHead = reader.readFileHead();
		if (flvHead != null) {
			System.out.println(flvHead);
			int i = 0;
			FLVTag temp = null;
			while (null != (temp = reader.readerTag())
					&& (line == -1 || line > i)) {
				i++;
				System.out.println(temp);
				// if (temp.getBody() != null && temp.getBody().getData() !=
				// null) {
				// for (int i = 0; i < 9; i++) {
				// System.out.print(Integer.toHexString(temp.getBody()
				// .getData()[i]) +" ");
				// }
				// System.out.println("\n");
				// }
			}
		}

		reader.close();
	}
}
