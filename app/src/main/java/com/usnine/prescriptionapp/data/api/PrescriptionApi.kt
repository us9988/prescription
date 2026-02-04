package com.usnine.prescriptionapp.data.api

import com.usnine.prescriptionapp.data.model.PrescriptionResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PrescriptionApi {

    // 이미지 보낼 때 multipart
    @Multipart
    @POST("analyze")
    suspend fun analyzePrescription(
        @Part file: MultipartBody.Part
    ): PrescriptionResponse
}
