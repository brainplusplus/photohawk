package eu.planets_project.pp.plato.evaluation.evaluators.imagecomparison.java.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.TileObserver;
import java.awt.image.WritableRaster;
import java.util.Vector;

/**
 * This class wraps a BufferedImage and provides some convenience as well as performance tweaks.
 * 
 * @author Stephan Bauer (stephan.bauer@student.tuwien.ac.at)
 * @version 1.0
 */
public class ConvenientBufferedImageWrapper implements RenderedImage {

	private BufferedImage bufferedImage;
	private Raster data;

	private void setBufferedImage(BufferedImage img) throws ImageException {
		if (null == img) {
			throw new IllegalArgumentException("Image not readable");
		}

		if (img.getWidth() <= 0 || img.getHeight() <= 0) {
			throw new IllegalArgumentException("Image size invalid");
		}

		// No longer performed checks
		/*-
		ColorModel cm = img.getColorModel();
		ColorSpace cs = cm.getColorSpace();

		if (img.getSampleModel().getNumBands() != 3) {
			throw new ImageFormatException("Invalid amount of channels");
		}
		 */

		/*-
		String[] names = new String[] { "red", "green", "blue" };
		char[] prefixes = new char[] { 'r', 'g', 'b' };
		for (int i = 0; i < 3; i++) {
			if (!cs.getName(i).toLowerCase().contains(names[i]) && !cs.getName(i).toLowerCase().trim().startsWith(String.valueOf(prefixes[i]))) {
				throw new ImageFormatException("Invalid channels");
			}
		}
		 */

		this.bufferedImage = img;

		data = this.bufferedImage.getData();

	}

	public ConvenientBufferedImageWrapper(BufferedImage img) throws ImageException {
		setBufferedImage(img);
	}

	public float[] getSample(int x, int y) {

		float[] result = new float[getSampleModel().getNumBands()];

		for (int i = 0; i < result.length; i++) {
			// System.out.println(getData().getSample(x, y, i));
			result[i] = getData().getSample(x, y, i) / (float) Math.pow(2, getColorModel().getComponentSize(i));
			// System.out.println(result[i]);
		}

		return result;

	}

	public Color getSRGBSample(int x, int y) {

		return new Color(getColorModel().getColorSpace(), getSample(x, y), 1.0f);

	}

	public BufferedImage getBufferedImage() {
		return this.bufferedImage;
	}

	/* ********* */
	/* DELEGATES */
	/* ********* */

	public synchronized Raster getData() {
		// Due to performance reasons
		return data;
	}

	public void addTileObserver(TileObserver to) {
		this.bufferedImage.addTileObserver(to);
	}

	public void coerceData(boolean isAlphaPremultiplied) {
		this.bufferedImage.coerceData(isAlphaPremultiplied);
	}

	public WritableRaster copyData(WritableRaster outRaster) {
		return this.bufferedImage.copyData(outRaster);
	}

	public Graphics2D createGraphics() {
		return this.bufferedImage.createGraphics();
	}

	public boolean equals(Object obj) {
		return this.bufferedImage.equals(obj);
	}

	public void flush() {
		this.bufferedImage.flush();
	}

	public float getAccelerationPriority() {
		return this.bufferedImage.getAccelerationPriority();
	}

	public WritableRaster getAlphaRaster() {
		return this.bufferedImage.getAlphaRaster();
	}

	public ImageCapabilities getCapabilities(GraphicsConfiguration gc) {
		return this.bufferedImage.getCapabilities(gc);
	}

	public ColorModel getColorModel() {
		return this.bufferedImage.getColorModel();
	}

	public Raster getData(Rectangle rect) {
		return this.bufferedImage.getData(rect);
	}

	public Graphics getGraphics() {
		return this.bufferedImage.getGraphics();
	}

	public int getHeight() {
		return this.bufferedImage.getHeight();
	}

	public int getHeight(ImageObserver observer) {
		return this.bufferedImage.getHeight(observer);
	}

	public int getMinTileX() {
		return this.bufferedImage.getMinTileX();
	}

	public int getMinTileY() {
		return this.bufferedImage.getMinTileY();
	}

	public int getMinX() {
		return this.bufferedImage.getMinX();
	}

	public int getMinY() {
		return this.bufferedImage.getMinY();
	}

	public int getNumXTiles() {
		return this.bufferedImage.getNumXTiles();
	}

	public int getNumYTiles() {
		return this.bufferedImage.getNumYTiles();
	}

	public Object getProperty(String name, ImageObserver observer) {
		return this.bufferedImage.getProperty(name, observer);
	}

	public Object getProperty(String name) {
		return this.bufferedImage.getProperty(name);
	}

	public String[] getPropertyNames() {
		return this.bufferedImage.getPropertyNames();
	}

	public WritableRaster getRaster() {
		return this.bufferedImage.getRaster();
	}

	public int[] getRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
		return this.bufferedImage.getRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}

	public int getRGB(int x, int y) {
		return this.bufferedImage.getRGB(x, y);
	}

	public SampleModel getSampleModel() {
		return this.bufferedImage.getSampleModel();
	}

	public Image getScaledInstance(int width, int height, int hints) {
		return this.bufferedImage.getScaledInstance(width, height, hints);
	}

	public ImageProducer getSource() {
		return this.bufferedImage.getSource();
	}

	public Vector<RenderedImage> getSources() {
		return this.bufferedImage.getSources();
	}

	public BufferedImage getSubimage(int x, int y, int w, int h) {
		return this.bufferedImage.getSubimage(x, y, w, h);
	}

	public Raster getTile(int tileX, int tileY) {
		return this.bufferedImage.getTile(tileX, tileY);
	}

	public int getTileGridXOffset() {
		return this.bufferedImage.getTileGridXOffset();
	}

	public int getTileGridYOffset() {
		return this.bufferedImage.getTileGridYOffset();
	}

	public int getTileHeight() {
		return this.bufferedImage.getTileHeight();
	}

	public int getTileWidth() {
		return this.bufferedImage.getTileWidth();
	}

	public int getTransparency() {
		return this.bufferedImage.getTransparency();
	}

	public int getType() {
		return this.bufferedImage.getType();
	}

	public int getWidth() {
		return this.bufferedImage.getWidth();
	}

	public int getWidth(ImageObserver observer) {
		return this.bufferedImage.getWidth(observer);
	}

	public WritableRaster getWritableTile(int tileX, int tileY) {
		return this.bufferedImage.getWritableTile(tileX, tileY);
	}

	public Point[] getWritableTileIndices() {
		return this.bufferedImage.getWritableTileIndices();
	}

	public int hashCode() {
		return this.bufferedImage.hashCode();
	}

	public boolean hasTileWriters() {
		return this.bufferedImage.hasTileWriters();
	}

	public boolean isAlphaPremultiplied() {
		return this.bufferedImage.isAlphaPremultiplied();
	}

	public boolean isTileWritable(int tileX, int tileY) {
		return this.bufferedImage.isTileWritable(tileX, tileY);
	}

	public void releaseWritableTile(int tileX, int tileY) {
		this.bufferedImage.releaseWritableTile(tileX, tileY);
	}

	public void removeTileObserver(TileObserver to) {
		this.bufferedImage.removeTileObserver(to);
	}

	public void setAccelerationPriority(float priority) {
		this.bufferedImage.setAccelerationPriority(priority);
	}

	public void setData(Raster r) {
		this.bufferedImage.setData(r);
	}

	public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
		this.bufferedImage.setRGB(startX, startY, w, h, rgbArray, offset, scansize);
	}

	public void setRGB(int x, int y, int rgb) {
		this.bufferedImage.setRGB(x, y, rgb);
	}

	public String toString() {
		return this.bufferedImage.toString();
	}

}
