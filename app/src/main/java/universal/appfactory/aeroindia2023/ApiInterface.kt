package universal.appfactory.aeroindia2023


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/v1/create")
    fun sendReq(@Body requestModel: RequestModel) : Call<ResponseModel>

}