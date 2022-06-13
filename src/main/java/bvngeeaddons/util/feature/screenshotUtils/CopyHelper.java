package bvngeeaddons.util.feature.screenshotUtils;

import java.awt.color.ColorSpace;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.*;
import java.awt.*;

public class CopyHelper {

    //Taken from Comp500's Screenshot To Clipboard fabric mod
    public static void doCopy(byte[] imageData, int width, int height, int components) {
        new Thread(() -> {
            DataBufferByte buf = new DataBufferByte(imageData, imageData.length);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
            // Ignore the alpha channel, due to JDK-8204187
            int[] numBits = {8, 8, 8};
            int[] bandOffsets = {0, 1, 2}; // is this efficient, no transformation is being done?
            ColorModel cm = new ComponentColorModel(cs, numBits, false, false,
                    Transparency.TRANSLUCENT,
                    DataBuffer.TYPE_BYTE);
            BufferedImage bufImg = new BufferedImage(cm, Raster.createInterleavedRaster(buf,
                    width, height,
                    width * components, components,
                    bandOffsets, null), false, null);

            Transferable trans = getTransferableImage(bufImg);
            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
            c.setContents(trans, null);
        }, "Screenshot to Clipboard Copy").start();
    }

    //Taken from Comp500's Screenshot To Clipboard fabric mod
    private static Transferable getTransferableImage(final BufferedImage bufferedImage) {
        return new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
                if (DataFlavor.imageFlavor.equals(flavor)) {
                    return bufferedImage;
                }
                throw new UnsupportedFlavorException(flavor);
            }
        };
    }

}
