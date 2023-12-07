import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.cioffi.nfctoogle.glance.ToogleWidgetReciver

class NFCToogleRefreshCallback  : ActionCallback  {

    companion object {
        const val UPDATE_ACTION = "updateAction"
    }

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val intent = Intent(context, ToogleWidgetReciver::class.java).apply {
            action = UPDATE_ACTION
        }
        context.sendBroadcast(intent)
    }
}