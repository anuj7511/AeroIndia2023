package universal.appfactory.aeroindia2023.agendas

import com.google.gson.annotations.SerializedName

data class AgendaResponse(@SerializedName("status")
                          val status: Int,
                          @SerializedName("message")
                          val message: String,
                          @SerializedName("data")
                          val data: List<AgendaModel>)
