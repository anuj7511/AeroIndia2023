package universal.appfactory.aeroindia2023
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("api/complaint-save")
    fun sendReq(@Body requestModel: RequestModel, @Header("Authorization") bearerToken: String) : Call<ResponseModel>

    @GET("api/get-speaker")
    fun getSpeakers(@Header("Authorization") bearerToken: String) : Call<SpeakerResponse?>?

    @POST("api/user")
    fun getExhibitors(@Header("Authorization") bearerToken: String) : Call<ExhibitorResponse?>?

    @GET("api/get-agenda")
    fun getAgendas(@Header("Authorization") bearerToken: String) : Call<AgendaResponse?>?

    @GET("api/get-products")
    fun getProducts(@Header("Authorization") bearerToken: String) : Call<ProductResponse?>?

    @POST("api/register-user")
    fun sendUserData(@Body userDataRequestModel: UserDataRequestModel,@Header("Authorization") bearerToken: String): Call<UserRegisterResponseModel>

    @POST("api/register-verify")
    //TODO: Params needs to be changed accordingly
    fun verifyUserData(@Body userDataRequestModel: UserDataRequestModel,@Header("Authorization") bearerToken: String): Call<UserRegisterResponseModel>

    @GET("api/get-complaint/1")
    fun getproblem(@Header("Authorization") bearerToken: String) :Call<ManagerResponse?>?

    @GET("api/get-complaint")
    fun getmanagers(@Header ("Authorisation") bearerToken: String) :Call<ZonalManagerResponse?>?

    @GET("api/get-users")
    fun gethistory(@Header("Authorisation") bearerToken: String) :Call<UserHistoryResponse?>?

    @GET("api/get-agenda/{id}")
    fun getAgendaSpeaker(@Header("Authorization") bearerToken: String, @Path("id") id: Int) : Call<SpeakerResponse?>?

    @GET("api/get-speaker/{id}")
    fun getSpeakerAgenda(@Header("Authorization") bearerToken: String, @Path("id") id: Int) : Call<AgendaResponse?>?
}