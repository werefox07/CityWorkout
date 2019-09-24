package com.example.feature_list_workout.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.Single
import java.io.FileOutputStream
import java.io.IOException

class ImageJpgToPngConverter(private var inputFileDir: String, private var inputFileName: String) {

    fun getPath(): String {
        if (!inputFileDir.endsWith("/")) {
            inputFileDir += "/"
        }
        return inputFileDir + inputFileName
    }

    fun createBitmapFromPath(): Single<Bitmap> {
        return Single.fromCallable {
            val path = getPath()
            val fileExt = path.substring(path.lastIndexOf("."))
            if ((fileExt != ".jpg") && (fileExt != ".jpeg")) {
                throw IOException()
            }
            BitmapFactory.decodeFile(path)
        }
    }

    fun compressBitmap(bitmap: Bitmap): Single<Bitmap> {
        return Single.fromCallable {
            val outFileName = getPath().substring(0, getPath().lastIndexOf(".")) + ".png"
            val outputStream = FileOutputStream(outFileName, false)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            bitmap.recycle()
            outputStream.flush()
            outputStream.close()
            bitmap
        }
    }
}