package eu.planets_project.pp.plato.evaluation.evaluators.imagecomparison.java.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eu.planets_project.pp.plato.util.CommandExecutor;

/**
 * This class provides wraps a raw image reader and provides some parameters to
 * influence the conversion.
 * 
 * @author Stephan Bauer (stephan.bauer@student.tuwien.ac.at)
 * @version 1.0
 */
public class RawImageReader {
	public static BufferedImage read(String file, ConversionParameter params) throws IOException {

		BufferedImage img = null;

		try {
			CommandExecutor exec = new CommandExecutor();
			int exitstatus = exec.runCommand("dcraw -i " + file);
			if (exitstatus == 0) {

				String command = "dcraw -c";

				if (params.isUse16Bit()) {
					command += " -T";
				}

				// Quickfix
				if (params.isUseTIFF()) {
					// command += " -6";
				}

				if (params.isUseFixedWhiteLevel()) {
					command += " -W";
				}

				if (params.isUseCameraWhiteBalance()) {
					command += " -w";
				}

				// Set wanted ColorSpace
				command += " -o " + params.getCs().getValue();

				// Set interpolation level
				if (!params.getInterpolation().getValue().equals("")) {
					command += " " + params.getInterpolation().getValue();
				}

				Process process = RawCommandExecutor.getProcess(command + " " + file);
				img = ImageIO.read(process.getInputStream());
				exitstatus = RawCommandExecutor.runProcess(process);

				if (exitstatus != 0) {
					throw new IOException("Error while converting raw");
				}
			}

		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

		return img;

	}

	public static class ConversionParameter {
		private boolean use16Bit;
		private boolean useTIFF;
		private ConversionColorSpace cs;
		private InterpretationType interpolation;
		private boolean useCameraWhiteBalance;
		private boolean useFixedWhiteLevel;

		public ConversionParameter() {
			this(true, true, ConversionColorSpace.sRGB, InterpretationType.ColorInterpolated, false, false);
		}

		public ConversionParameter(boolean use16Bit, boolean useTIFF, ConversionColorSpace cs,
				InterpretationType interpolation, boolean useCameraWhitebalance, boolean useFixedWhiteLevel) {
			this.use16Bit = use16Bit;
			this.useTIFF = useTIFF;
			this.cs = cs;
			this.interpolation = interpolation;
			this.useCameraWhiteBalance = useCameraWhitebalance;
			this.useFixedWhiteLevel = useFixedWhiteLevel;
		}

		public boolean isUse16Bit() {
			return this.use16Bit;
		}

		public boolean isUseTIFF() {
			return this.useTIFF;
		}

		public boolean isUseCameraWhiteBalance() {
			return this.useCameraWhiteBalance;
		}

		public boolean isUseFixedWhiteLevel() {
			return this.useFixedWhiteLevel;
		}

		public ConversionColorSpace getCs() {
			return this.cs;
		}

		public InterpretationType getInterpolation() {
			return this.interpolation;
		}

		public enum ConversionColorSpace {
			NoConversion(0), sRGB(1), AdobeRGB(2), WideGamutRGB(3), KodakProPhoto(4), XYZ(5);

			private final int value;

			ConversionColorSpace(int value) {
				this.value = value;
			}

			private int getValue() {
				return value;
			}
		}

		public enum InterpretationType {
			NoInterpretation("-D"), Delinearize("-d"), ColorInterpolated("");

			private final String value;

			InterpretationType(String value) {
				this.value = value;
			}

			private String getValue() {
				return value;
			}
		}
	}
}
