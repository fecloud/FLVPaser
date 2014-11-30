package cn.yuncore;

/**
 * 工具类
 * 
 * @author Administrator
 * 
 */
public final class Utils {

	/**
	 * tag类型
	 * 
	 * @param type
	 * @return
	 */
	public static String getTagType(int type) {
		switch (type) {
		case 0x8:
			return "audio";
		case 0x9:
			return "video";
		case 0x12:
			return "script";
		default:
			break;
		}
		return null;
	}

	/**
	 * tag body 解码器类型
	 * 
	 * @param codec
	 * @return
	 */
	public static String getVideoCodecName(int codec) {
		switch (codec) {
		case 2:
			return "seronson h.263";
		case 3:
			return "screen video";
		case 4:
			return "On2 VP6";
		case 5:
			return "On2 VP6 with alpha channel";
		case 6:
			return "Screen video version 2";
		case 7:
			return "AVC (h.264)";
		default:
			break;
		}
		return null;
	}

	/**
	 * 取音频格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getAudioFormat(int format) {
		switch (format) {
		case 0:
			return "UNCOMPREES";
		case 1:
			return "ADPCM";
		case 2:
			return "MP3";
		case 4:
			return "Nellymoser 16-kHz mono";
		case 5:
			return "Nellymoser 8-kHz mono";
		case 10:
			return "AAC";
		default:
			break;
		}
		return null;
	}

	public static String getAudioSamplerate(int samplerate) {

		switch (samplerate) {
		case 0:
			return "5.5kHz";
		case 1:
			return "11kHz";
		case 2:
			return "22kHz";
		case 3:
			return "44kHz";
		default:
			break;
		}
		return null;

	}

	/**
	 * 音频采样率
	 * 
	 * @param snd
	 * @return
	 */
	public static String getAudioSnd(int snd) {
		switch (snd) {
		case 0:
			return "snd8Bit";
		case 1:
			return "snd16Bit";
		default:
			break;
		}
		return null;
	}

	public static String getAudioSndType(int sndType) {
		switch (sndType) {
		case 0:
			return "sndMomo";
		case 1:
			return "sndStereo";
		default:
			break;
		}
		return null;
	}

	/**
	 * 取视频帧类型
	 * @param frame
	 * @return
	 */
	public static String getVideoFrame(int frame) {
		switch (frame) {
		case 1:
			return "keyframe";
		case 2:
			return "inner frame";
		case 3:
			return "disposable inner frame （h.263 only）";
		case 4:
			return "generated keyframe";
		default:
			break;
		}
		return null;
	}

}
