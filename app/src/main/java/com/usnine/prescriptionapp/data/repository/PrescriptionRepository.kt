package com.usnine.prescriptionapp.data.repository

import android.content.Context
import android.net.Uri
import com.usnine.prescriptionapp.data.api.PrescriptionApi
import com.usnine.prescriptionapp.data.model.PrescriptionResponse
import com.usnine.prescriptionapp.util.FileUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrescriptionRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: PrescriptionApi
) {

    suspend fun analyzePrescription(imageFile: File): Result<PrescriptionResponse> {
        return try {
            val requestBody = imageFile.asRequestBody("image/*".toMediaType())
            val part = MultipartBody.Part.createFormData("file", imageFile.name, requestBody)

            val response = api.analyzePrescription(part)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun analyzePrescription(uri: Uri): Result<PrescriptionResponse> {
        val file = FileUtil.uriToFile(context, uri)
            ?: return Result.failure(Exception("파일을 읽을 수 없습니다."))
        return analyzePrescription(file)
    }
}
