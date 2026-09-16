package com.wahidiyah.miladiyyah.core.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DanaBoxBootReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        if (
            intent.action ==
            Intent.ACTION_BOOT_COMPLETED
        ) {
            DanaBoxNotificationHelper.ensureChannel(context)
            DanaBoxNotificationScheduler.scheduleUpcoming(context)
        }
    }
}