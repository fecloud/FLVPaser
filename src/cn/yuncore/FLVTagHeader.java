package cn.yuncore;

/**
 * tag header
 * @author Administrator
 *
 */
public class FLVTagHeader {

	/**
	 * 1��4���ֽ�Ϊ��һ��tag���ܳ���
	 */
	private int previousTagSize;

	/**
	 * ��Ƶ��0x8������Ƶ��0x9�����ű���0x12��
	 */
	private int type;

	/**
	 * flvbody�ĳ���
	 */
	private int dataLength;

	/**
	 * ��һ֡������Ƶ��ʱ���
	 */
	private int timestamp;

	/**
	 * ��id(��������Ϊ0)
	 */
	private int streamid;

	/**
	 * tag body �������ļ�����ʼλ��
	 */
	private long dataPostion;

	public int getPreviousTagSize() {
		return previousTagSize;
	}

	public void setPreviousTagSize(int previousTagSize) {
		this.previousTagSize = previousTagSize;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getStreamid() {
		return streamid;
	}

	public void setStreamid(int streamid) {
		this.streamid = streamid;
	}

	public long getDataPostion() {
		return dataPostion;
	}

	public void setDataPostion(long dataPostion) {
		this.dataPostion = dataPostion;
	}

	@Override
	public String toString() {
		return "FLVHeader [previousTagSize=" + previousTagSize + ", type=" + Utils.getTagType(type)
				+ ", dataLength=" + dataLength + ", timestamp=" + timestamp
				+ ", streamid=" + streamid + ", dataPostion=" + dataPostion
				+ "]";
	}

}
