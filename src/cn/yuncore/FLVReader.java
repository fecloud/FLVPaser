package cn.yuncore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FLVReader {

	/**
	 * ��ȡtaghead
	 */
	private RandomAccessFile in;

	private long fileLength;

	public FLVReader(File file) {
		super();
		if (file != null && file.exists()) {
			try {
				fileLength = file.length();
				in = new RandomAccessFile(file, "r");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ȡtaghead
	 * 
	 * @return
	 * @throws IOException
	 */
	public FLVFileHead readFileHead() throws IOException {

		/**
		 * FLV�ļ�ͷ9���ֽ�
		 */
		final byte[] bs = new byte[9];
		if (in.read(bs) == bs.length) {
			/**
			 * �Ƿ�����Ƶ��Ƶ�ڵ�5���ֽ�
			 */
			byte av = bs[4];
			final FLVFileHead head = new FLVFileHead();
			/**
			 * ��5���ֽڵĵ�1λΪ1 ˵������Ƶ
			 */
			if ((av & 0x1) == 0x1) {
				head.setHaveVideo(true);
			}
			/**
			 * ��5���ֽڵĵ�4λΪ1 ˵������Ƶ
			 */
			if ((av & 0x4) == 0x4) {
				head.setHaveAudio(true);
			}
			return head;
		}
		return null;

	}

	/**
	 * ��ȡFLV�ļ�tag
	 * 
	 * @return
	 * @throws IOException
	 */
	public FLVTag readerTag() throws IOException {
		final FLVTag flvTag = new FLVTag();
		flvTag.setHeader(readFLVTagHeader());
		if (flvTag.getHeader() == null) {
			return null;
		}
		readFLVTagBody(flvTag);
		return flvTag;
	}

	private FLVTagHeader readFLVTagHeader() throws IOException {
		if (in.getFilePointer() != fileLength) {
			int previousTagSize = in.readInt();
			if (previousTagSize != -1) {
				final FLVTagHeader flvTagHeader = new FLVTagHeader();
				flvTagHeader.setPreviousTagSize(previousTagSize);
				// ÿһ��tag head Ϊ11���ֽ�(��RTMP����)
				final byte[] bs = new byte[11];
				if (in.read(bs) == bs.length) {
					// tag �ڵ�һ���ֽ�
					flvTagHeader.setType(bs[0]);
					// tag body ����Ϊ234���ֽ�
					flvTagHeader.setDataLength(bytesToInt(bs[1], bs[2], bs[3]));
					// ���ʱ���3λ������ռ��4��λ
					if (bs[7] != 0) {
						// 4��λ����ʱ���
						flvTagHeader.setTimestamp(bytesToInt(bs[4], bs[5],
								bs[6], bs[7]));
					} else {
						// 3��λ��ʱ���
						flvTagHeader.setTimestamp(bytesToInt(bs[4], bs[5],
								bs[6]));
					}

					flvTagHeader.setStreamid(bytesToInt(bs[8], bs[9], bs[10]));
					flvTagHeader.setDataPostion(in.getFilePointer());
					return flvTagHeader;
				}
			}
		}
		return null;
	}

	/**
	 * ��ȡFLVtagbody
	 * 
	 * @param tag
	 * @return
	 * @throws IOException
	 */
	private void readFLVTagBody(FLVTag tag) throws IOException {
		if (tag != null && tag.getHeader() != null
				&& tag.getHeader().getDataLength() > 0
				&& tag.getHeader().getDataPostion() > -1) {
			final byte[] bs = new byte[tag.getHeader().getDataLength()];
			if ((in.read(bs)) == bs.length) {
				FLVTagBody body = null;

				if (tag.getHeader().getType() == 0x8) {
					final FLVAudioTagBody audioTagBody = new FLVAudioTagBody();
					audioTagBody.setData(bs);
					paserAudioBody(audioTagBody);
					body = audioTagBody;
				} else if (tag.getHeader().getType() == 0x9) {
					final FLVVideoTagBody flvVideoTagBody = new FLVVideoTagBody();
					flvVideoTagBody.setData(bs);
					paserVideoBody(flvVideoTagBody);
					body = flvVideoTagBody;
				}

				tag.setBody(body);
			}

		}

	}

	/**
	 * ������Ƶ������Ϣ
	 * 
	 * @param flvAudioTagBody
	 */
	private void paserAudioBody(FLVAudioTagBody flvAudioTagBody) {

		if (flvAudioTagBody.getData() != null
				&& flvAudioTagBody.getData().length > 0) {
			final byte audio = flvAudioTagBody.getData()[0];
			flvAudioTagBody.setFormat((0xF0 & audio) >> 4);
			flvAudioTagBody.setSamplerate((0x0C & audio) >> 2);
			flvAudioTagBody.setSnd((0x2 & audio) >> 1);
			flvAudioTagBody.setSndType(0x1 & audio);
		}

	}

	/**
	 * ������Ƶ������Ϣ
	 * 
	 * @param flvVideoTagBody
	 */
	private void paserVideoBody(FLVVideoTagBody flvVideoTagBody) {

		if (flvVideoTagBody.getData() != null
				&& flvVideoTagBody.getData().length > 0) {
			final byte video = flvVideoTagBody.getData()[0];
			flvVideoTagBody.setFrameType((0xF0 & video) >> 4);
			flvVideoTagBody.setCodec(0x0F & video);
		}

	}

	/**
	 * byte ת��int
	 * 
	 * @param bytes
	 * @return
	 */
	public static int bytesToInt(byte... bytes) {
		int value = 0;
		for (int i = 0; i < bytes.length; i++) {
			int shift = (bytes.length - 1 - i) * 8;
			value += (bytes[i + 0] & 0x000000FF) << shift;
		}
		return value;
	}

	/**
	 * �ر�reader
	 */
	public void close() {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
