package cn.yuncore;

import java.io.File;
import java.io.IOException;

/**
 * ¶ÁÈ¡FLVÎÄ¼þtag
 * @author Administrator
 *
 */
public class FlvToH264 {

	public static void main(String[] args) throws IOException {

		FLVReader reader = new FLVReader(new File("test.flv"));
		final FLVFileHead flvHead = reader.readFileHead();
		if (flvHead != null) {
			System.out.println(flvHead);

			FLVTag temp = null;
			while (null != (temp = reader.readerTag())) {
				System.out.println(temp);
			}
		}

		reader.close();
	}

}
