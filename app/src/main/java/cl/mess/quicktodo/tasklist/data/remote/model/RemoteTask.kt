package cl.mess.quicktodo.tasklist.data.remote.model

import cl.mess.quicktodo.tasklist.data.remote.model.Constants.COMPLETED
import cl.mess.quicktodo.tasklist.data.remote.model.Constants.ID
import cl.mess.quicktodo.tasklist.data.remote.model.Constants.TITLE
import cl.mess.quicktodo.tasklist.data.remote.model.Constants.USER_ID
import com.google.gson.annotations.SerializedName

data class RemoteTask(
    @SerializedName(USER_ID) val userId: Int?,
    @SerializedName(ID) val id: Int?,
    @SerializedName(TITLE) val title: String?,
    @SerializedName(COMPLETED) val completed: Boolean?
)
