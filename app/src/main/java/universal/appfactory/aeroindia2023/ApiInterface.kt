package universal.appfactory.aeroindia2023
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/complaint-save")
    fun sendReq(@Body requestModel: RequestModel) : Call<ResponseModel>

    @GET("api/get-speaker")
    fun getSpeakers(@Header("Authorization") bearerToken: String) : Call<SpeakerResponse?>?

    @GET("api/get-exhibitors")
    fun getExhibitors(@Header("Authorization") bearerToken: String) : Call<ExhibitorResponse?>?

    @GET("api/get-agenda")
    fun getAgenda(@Header("Authorization") bearerToken: String) : Call<AgendaResponse?>?

    @GET("api/get-products")
    fun getProducts(@Header("Authorization") bearerToken: String) : Call<ProductResponse?>?
}