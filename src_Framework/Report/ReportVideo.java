package Report;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import ConstantsFramework.Constants_Framework;

public class ReportVideo {
	ScreenRecorder screenRecorder;
	
	
	/**
	 * Construtor da classe ReportVideo
	 * @author fernando.f.andrade
	 */
	public ReportVideo() throws IOException, AWTException {
		// set the graphics configuration
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		// initialize the screen recorder:
		// - default graphics configuration
		// - full screen recording
		// - record in AVI format
		// - 15 frames per second
		// - black mouse pointer
		// - no audio
		// - save capture to predefined location

		String targetFolder =Constants_Framework.VIDEO_PATH;
		screenRecorder = new ScreenRecorder(gc, gc.getBounds(),
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, new File(targetFolder));
		

	}
	/**
	 * Método para iniciar a gravação do video.
	 * @return void
	 * @author fernando.f.andrade
	 */

	public void start() throws IOException, AWTException {

		screenRecorder.start();
	}
	/**
	 * Método para parar a gravação do video.
	 * @return void
	 * @author fernando.f.andrade
	 */
	public void stop() throws IOException {
		screenRecorder.stop();
	}

}