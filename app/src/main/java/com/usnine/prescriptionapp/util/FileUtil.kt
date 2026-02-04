package com.usnine.prescriptionapp.util

import android.content.Context
import android.net.Uri
import java.io.File

object FileUtil {

    fun uriToFile(context: Context, uri: Uri): File? {
        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                val tempFile = File.createTempFile("prescription_", ".jpg", context.cacheDir)
                tempFile.outputStream().use { output ->
                    inputStream.copyTo(output)
                }
                tempFile
            }
        } catch (e: Exception) {
            null
        }
    }
}
