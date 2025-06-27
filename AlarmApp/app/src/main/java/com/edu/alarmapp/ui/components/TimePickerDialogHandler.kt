package com.edu.alarmapp.ui.components

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun TimePickerDialogHandler(
    show: Boolean,
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    if (show) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                onTimeSelected(selectedHour, selectedMinute)
            },
            hour,
            minute,
            true
        ).apply {
            setOnDismissListener { onDismiss() }
            show()
        }
    }
}
