package com.alamkanak.weekview.sample

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.alamkanak.weekview.WeekViewEvent
import kotlinx.android.synthetic.main.activity_base.*
import java.util.*

/**
 * A basic example of how to use week view library.
 */
open class BasicActivity : BaseActivity() {
    var uniqueId: Long = 0
    private fun getUniqueId(): String = uniqueId++.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weekView.eventBorderWidth = 2f
        weekView.isShowNowLine = true
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            title = ""
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onMonthChange(newYear: Int, newMonth: Int): MutableList<out WeekViewEvent>? {
        // Populate the week view with some events.
        val events = ArrayList<WeekViewEvent>()

        var startTime = Calendar.getInstance()
        startTime.set(2020, 8, 1, 3, 0)
        var endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 1, 4, 0)
        var event = WeekViewEvent(id = getUniqueId(), name = getEventTitle(startTime, endTime), startTime = startTime, endTime = endTime)
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.location = "A倉庫 -> B倉庫"
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 1, 2, 0)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 1, 3, 30)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 1, 1, 0)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 1, 4, 0)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 1, 2, 30)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 1, 4, 30)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 1, 6, 0)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 1, 9, 30)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_06)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_06)
        events.add(event)
        /*var startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        var endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR, 1)
        endTime.set(Calendar.MONTH, newMonth - 1)
        var event = WeekViewEvent("First", getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_01, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR_OF_DAY) + 1)
        endTime.set(Calendar.MINUTE, 0)
        endTime.set(Calendar.MONTH, newMonth - 1)
        event = WeekViewEvent("cur", "cur", startTime, endTime)
        event.color = 0xffff0000.toInt()
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 4)
        endTime.set(Calendar.MINUTE, 30)
        endTime.set(Calendar.MONTH, newMonth - 1)
        event = WeekViewEvent("Second", getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_05, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 2)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 4)
        endTime.set(Calendar.MINUTE, 0)
        endTime.set(Calendar.MONTH, newMonth - 1)
        event = WeekViewEvent("third", getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_03, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 4)
        startTime.set(Calendar.MINUTE, 20)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 5)
        endTime.set(Calendar.MINUTE, 0)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_03, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 5)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 2)
        endTime.set(Calendar.MONTH, newMonth - 1)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_02, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 5)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        startTime.add(Calendar.DATE, 1)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_03, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 15)
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_04, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 1)
        startTime.set(Calendar.HOUR_OF_DAY, 3)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_01, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH))
        startTime.set(Calendar.HOUR_OF_DAY, 15)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.add(Calendar.HOUR_OF_DAY, 3)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime), startTime, endTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_02, null)
        events.add(event)

        //AllDay event
        //single day
        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 0)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, allDay = true), null, startTime)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_04, null)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 8)
        startTime.set(Calendar.HOUR_OF_DAY, 2)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.DAY_OF_MONTH, 10)
        endTime.set(Calendar.HOUR_OF_DAY, 23)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime, true), null, startTime, endTime, true)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_03, null)
        events.add(event)

        // All day event until 00:00 next day
        startTime = Calendar.getInstance()
        startTime.set(Calendar.DAY_OF_MONTH, 10)
        startTime.set(Calendar.HOUR_OF_DAY, 0)
        startTime.set(Calendar.MINUTE, 0)
        startTime.set(Calendar.SECOND, 0)
        startTime.set(Calendar.MILLISECOND, 0)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.DAY_OF_MONTH, 11)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime, true), null, startTime, endTime, true)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_01, null)

        startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 18)
        startTime.set(Calendar.MINUTE, 30)
        startTime.set(Calendar.MONTH, newMonth - 1)
        startTime.set(Calendar.YEAR, newYear)
        endTime = startTime.clone() as Calendar
        endTime.set(Calendar.HOUR_OF_DAY, 19)
        endTime.set(Calendar.MINUTE, 30)
        endTime.set(Calendar.MONTH, newMonth - 1)
        event = WeekViewEvent(getUniqueId(), getEventTitle(startTime, endTime, true), null, startTime, endTime, true)
        event.color = ResourcesCompat.getColor(resources, R.color.event_color_02, null)
        events.add(event)
*/
        return events
    }

}
