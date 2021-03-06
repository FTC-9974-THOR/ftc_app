package org.firstinspires.ftc.teamcode.cv;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgproc.*;

/**
* GlyphPipeline class.
*
* <p>An OpenCV pipeline generated by GRIP. Fixed by christopher.johnson.
*
* @author GRIP
*/
public class GlyphPipeline {

	//Outputs
	private Mat blurOutput = new Mat();
	private Mat hsvThreshold0Output = new Mat();
	private Mat hsvThreshold1Output = new Mat();
	private Mat switchOutput = new Mat();
	private Mat cvErodeOutput = new Mat();
	private Mat cvDilateOutput = new Mat();
	private Mat maskOutput = new Mat();
	private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();

	private boolean switchSwitch = true;

	/**
	 * This is the primary method that runs the entire pipeline and updates the outputs.
	 */
	public void process(Mat source0) {
		// Step Blur0:
		Mat blurInput = source0;
		BlurType blurType = BlurType.get("Box Blur");
		double blurRadius = 7.207207207207208;
		blur(blurInput, blurType, blurRadius, blurOutput);

		// Step HSV_Threshold0:
		Mat hsvThreshold0Input = blurOutput;
		double[] hsvThreshold0Hue = {0.0, 36.3667232597623};
		double[] hsvThreshold0Saturation = {80.07557449851464, 159.22209768743363};
		double[] hsvThreshold0Value = {57.82198132856396, 111.59573821439652};
		hsvThreshold(hsvThreshold0Input, hsvThreshold0Hue, hsvThreshold0Saturation, hsvThreshold0Value, hsvThreshold0Output);

		// Step HSV_Threshold1:
		Mat hsvThreshold1Input = blurOutput;
		double[] hsvThreshold1Hue = {15.254464486468438, 25.411334302552138};
		double[] hsvThreshold1Saturation = {0.2616169597619864, 30.350252795944368};
		double[] hsvThreshold1Value = {184.08473173598657, 195.74566675880484};
		hsvThreshold(hsvThreshold1Input, hsvThreshold1Hue, hsvThreshold1Saturation, hsvThreshold1Value, hsvThreshold1Output);

		// Step Switch0:
		//Inputs
		Mat switchIfTrue = hsvThreshold0Output;
		Mat switchIfFalse = hsvThreshold1Output;
		//Output
		Ref<Mat> switchOutputRef = new Ref<Mat>();
		pipelineSwitch(switchSwitch, switchIfTrue,
		switchIfFalse, switchOutputRef);
		//output assignment
		switchOutput = switchOutputRef.get();
		// Step CV_erode0:
		Mat cvErodeSrc = switchOutput;
		Mat cvErodeKernel = new Mat();
		Point cvErodeAnchor = new Point(-1, -1);
		double cvErodeIterations = 5.0;
		int cvErodeBordertype = Core.BORDER_CONSTANT;
		Scalar cvErodeBordervalue = new Scalar(-1);
		cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue, cvErodeOutput);

		// Step CV_dilate0:
		Mat cvDilateSrc = cvErodeOutput;
		Mat cvDilateKernel = new Mat();
		Point cvDilateAnchor = new Point(-1, -1);
		double cvDilateIterations = 19.0;
		int cvDilateBordertype = Core.BORDER_CONSTANT;
		Scalar cvDilateBordervalue = new Scalar(-1);
		cvDilate(cvDilateSrc, cvDilateKernel, cvDilateAnchor, cvDilateIterations, cvDilateBordertype, cvDilateBordervalue, cvDilateOutput);

		// Step Mask0:
		Mat maskInput = source0;
		Mat maskMask = cvDilateOutput;
		mask(maskInput, maskMask, maskOutput);

		// Step Find_Contours0:
		Mat findContoursInput = cvDilateOutput;
		boolean findContoursExternalOnly = false;
		findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

	}

	/**
	 * This method is a generated setter for the condition of Switch
	 * @param value the condition to set
	 */
	 public void setswitch0(boolean value) {
	 	switchSwitch = value;
	 }

	/**
	 * This method is a generated getter for the output of a Blur.
	 * @return Mat output from Blur.
	 */
	public Mat blurOutput() {
		return blurOutput;
	}

	/**
	 * This method is a generated getter for the output of a HSV_Threshold.
	 * @return Mat output from HSV_Threshold.
	 */
	public Mat hsvThreshold0Output() {
		return hsvThreshold0Output;
	}

	/**
	 * This method is a generated getter for the output of a HSV_Threshold.
	 * @return Mat output from HSV_Threshold.
	 */
	public Mat hsvThreshold1Output() {
		return hsvThreshold1Output;
	}

	/**
	 * This method is a generated getter for the output of a Switch.
	 * @return Mat output from Switch.
	 */
	public Mat switchOutput() {
		return switchOutput;
	}

	/**
	 * This method is a generated getter for the output of a CV_erode.
	 * @return Mat output from CV_erode.
	 */
	public Mat cvErodeOutput() {
		return cvErodeOutput;
	}

	/**
	 * This method is a generated getter for the output of a CV_dilate.
	 * @return Mat output from CV_dilate.
	 */
	public Mat cvDilateOutput() {
		return cvDilateOutput;
	}

	/**
	 * This method is a generated getter for the output of a Mask.
	 * @return Mat output from Mask.
	 */
	public Mat maskOutput() {
		return maskOutput;
	}

	/**
	 * This method is a generated getter for the output of a Find_Contours.
	 * @return ArrayList<MatOfPoint> output from Find_Contours.
	 */
	public ArrayList<MatOfPoint> findContoursOutput() {
		return findContoursOutput;
	}


	/**
	 * An indication of which type of filter to use for a blur.
	 * Choices are BOX, GAUSSIAN, MEDIAN, and BILATERAL
	 */
	enum BlurType{
		BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
			BILATERAL("Bilateral Filter");

		private final String label;

		BlurType(String label) {
			this.label = label;
		}

		public static BlurType get(String type) {
			if (BILATERAL.label.equals(type)) {
				return BILATERAL;
			}
			else if (GAUSSIAN.label.equals(type)) {
			return GAUSSIAN;
			}
			else if (MEDIAN.label.equals(type)) {
				return MEDIAN;
			}
			else {
				return BOX;
			}
		}

		@Override
		public String toString() {
			return this.label;
		}
	}

	/**
	 * Softens an image using one of several filters.
	 * @param input The image on which to perform the blur.
	 * @param type The blurType to perform.
	 * @param doubleRadius The radius for the blur.
	 * @param output The image in which to store the output.
	 */
	private void blur(Mat input, BlurType type, double doubleRadius,
		Mat output) {
		int radius = (int)(doubleRadius + 0.5);
		int kernelSize;
		switch(type){
			case BOX:
				kernelSize = 2 * radius + 1;
				Imgproc.blur(input, output, new Size(kernelSize, kernelSize));
				break;
			case GAUSSIAN:
				kernelSize = 6 * radius + 1;
				Imgproc.GaussianBlur(input,output, new Size(kernelSize, kernelSize), radius);
				break;
			case MEDIAN:
				kernelSize = 2 * radius + 1;
				Imgproc.medianBlur(input, output, kernelSize);
				break;
			case BILATERAL:
				Imgproc.bilateralFilter(input, output, -1, radius, radius);
				break;
		}
	}

	/**
	 * Segment an image based on hue, saturation, and value ranges.
	 *
	 * @param input The image on which to perform the HSL threshold.
	 * @param hue The min and max hue
	 * @param sat The min and max saturation
	 * @param val The min and max value
	 * @param out The image in which to store the output.
	 */
	private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val,
	    Mat out) {
		Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
		Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
			new Scalar(hue[1], sat[1], val[1]), out);
	}

	/**
	 * Selects an output from two inputs based on a boolean.
	 * @param sw The boolean that determines the output.
	 * @param onTrue The output if sw is true.
	 * @param onFalse The output if sw is false.
	 * @param output The output which is equal to either onTrue or onFalse.
	 */
	private <T> void pipelineSwitch(boolean sw, T onTrue, T onFalse, Ref<T> output) {
		if (sw) {
			output.set(onTrue);
		}
		else {
			output.set(onFalse);
		}
	}

	/**
	 * Expands area of lower value in an image.
	 * @param src the Image to erode.
	 * @param kernel the kernel for erosion.
	 * @param anchor the center of the kernel.
	 * @param iterations the number of times to perform the erosion.
	 * @param borderType pixel extrapolation method.
	 * @param borderValue value to be used for a constant border.
	 * @param dst Output Image.
	 */
	private void cvErode(Mat src, Mat kernel, Point anchor, double iterations,
		int borderType, Scalar borderValue, Mat dst) {
		if (kernel == null) {
			kernel = new Mat();
		}
		if (anchor == null) {
			anchor = new Point(-1,-1);
		}
		if (borderValue == null) {
			borderValue = new Scalar(-1);
		}
		Imgproc.erode(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
	}

	/**
	 * Expands area of higher value in an image.
	 * @param src the Image to dilate.
	 * @param kernel the kernel for dilation.
	 * @param anchor the center of the kernel.
	 * @param iterations the number of times to perform the dilation.
	 * @param borderType pixel extrapolation method.
	 * @param borderValue value to be used for a constant border.
	 * @param dst Output Image.
	 */
	private void cvDilate(Mat src, Mat kernel, Point anchor, double iterations,
	int borderType, Scalar borderValue, Mat dst) {
		if (kernel == null) {
			kernel = new Mat();
		}
		if (anchor == null) {
			anchor = new Point(-1,-1);
		}
		if (borderValue == null){
			borderValue = new Scalar(-1);
		}
		Imgproc.dilate(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
	}

	/**
	 * Filter out an area of an image using a binary mask.
	 * @param input The image on which the mask filters.
	 * @param mask The binary image that is used to filter.
	 * @param output The image in which to store the output.
	 */
	private void mask(Mat input, Mat mask, Mat output) {
		mask.convertTo(mask, CvType.CV_8UC1);
		Core.bitwise_xor(output, output, output);
		input.copyTo(output, mask);
	}

	/**
	 * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
	 * @param input The image on which to perform the Distance Transform.
	 * @param externalOnly If false, contours within contours are allowed
	 * @param contours The list in which to store the output.
	 */
	private void findContours(Mat input, boolean externalOnly,
		List<MatOfPoint> contours) {
		Mat hierarchy = new Mat();
		contours.clear();
		int mode;
		if (externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(input, contours, hierarchy, mode, method);
	}


	/**
	 * Enables C-style output parameters in Java to avoid creating custom data classes for each
	 * operation.
	 *
	 * <p>Syntax is {@code Ref<T> varName = new Ref<T>(initValue)}.
	 * Where varName is the name of the variable and initValue is of type T and contains initial value.
	 * </p>
	 * @param <T> The type of object being referenced
	 */
	private static class Ref<T> {
		private T value;

		/**
		 * Constructor for a Ref object.
		 * @param initValue Type T initial value for the object.
		 */
		public Ref(T initValue) {
			value = initValue;
		}

		/**
		 * Constructor for a Ref object without an initial value.
		 * Equivalent to calling Ref(null)
		 */
		public Ref() {
			this(null);
		}

		/**
		 * Sets the object to contain a new value.
		 *
		 * @param newValue the new value being referenced
		 */
		public void set(T newValue) {
			value = newValue;
		}

		/**
		 * Gets the current referenced value
		 *
		 * @return the current referenced value
		 */
		public T get() {
			return value;
		}
	}

}

