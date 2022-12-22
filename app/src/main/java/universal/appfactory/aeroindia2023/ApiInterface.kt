package universal.appfactory.aeroindia2023
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/complaint-save")
    fun sendReq(@Body requestModel: RequestModel,@Header("Authorization") bearerToken: String) : Call<ResponseModel>

    @GET("api/get-speaker")
    fun getSpeakers(@Header("Authorization") bearerToken: String) : Call<SpeakerResponse?>?

    @GET("api/get-exhibitors")
    fun getExhibitors(@Header("Authorization") bearerToken: String) : Call<ExhibitorResponse?>?

    @GET("api/get-agenda")
    fun getAgenda(@Header("Authorization") bearerToken: String) : Call<AgendaResponse?>?

    @GET("api/get-complaint/1")
    fun getproblem(@Header("Authorization") bearerToken: String) :Call<ManagerResponse?>?

    @GET("api/get-complaint")
    fun getmanagers(@Header ("Authorisation") bearerToken: String) :Call<ZonalManagerResponse?>?

    @GET("api/get-users")
    fun gethistory(@Header("Authorisation") bearerToken: String) :Call<UserHistoryResponse?>?
}