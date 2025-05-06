import android.content.Context
import android.content.Intent
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.cioffi.nfctoggle.glance.ToggleWidgetReciver

class NFCToggleRefreshCallback  : ActionCallback  {

    companion object {
        const val UPDATE_ACTION = "updateAction"
    }

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val intent = Intent(context, ToggleWidgetReciver::class.java).apply {
            action = UPDATE_ACTION
        }
        context.sendBroadcast(intent)
    }
}