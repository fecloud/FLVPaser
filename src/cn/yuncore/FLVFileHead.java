package cn.yuncore;

/**
 * �ļ�ͷ
 * @author Administrator
 *
 */
public class FLVFileHead {

	/**
	 * �Ƿ�����Ƶ
	 */
	public boolean haveAudio;
	
	/**
	 * �Ƿ�����Ƶ
	 */
	public boolean haveVideo;

	public boolean isHaveAudio() {
		return haveAudio;
	}

	public void setHaveAudio(boolean haveAudio) {
		this.haveAudio = haveAudio;
	}

	public boolean isHaveVideo() {
		return haveVideo;
	}

	public void setHaveVideo(boolean haveVideo) {
		this.haveVideo = haveVideo;
	}

	@Override
	public String toString() {
		return "FLVHead [haveAudio=" + haveAudio + ", haveVideo=" + haveVideo
				+ "]";
	}
	
	
}
