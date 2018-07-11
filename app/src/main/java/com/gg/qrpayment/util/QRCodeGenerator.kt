package com.gg.qrpayment.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

class QRCodeGenerator(var code: String, var size: Int, private var background: Int, private var foreground: Int) {

    init {
        setCode()
    }

    lateinit var bitMatrix: BitMatrix
    var myBitmap: Bitmap? = null
    @Throws(WriterException::class)
    fun setCode() {
        try {
            bitMatrix = MultiFormatWriter().encode(code, BarcodeFormat.QR_CODE, this.size, this.size, null
            )
        } catch (Illegalargumentexception: IllegalArgumentException) {
            return
        }
        val bitMatrixWidth = bitMatrix.width
        val bitMatrixHeight = bitMatrix.height
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix.get(x, y)) foreground else background
            }
        }
        myBitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        myBitmap?.setPixels(pixels, 0, size, 0, 0, bitMatrixWidth, bitMatrixHeight)
    }

    fun getBitmap(): Bitmap? {
        return myBitmap
    }
}