package com.alamkanak.weekview.sample

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.view.Menu
import android.view.View
import androidx.core.content.ContextCompat
import com.alamkanak.weekview.WeekViewEvent
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_base.*
import org.threeten.bp.DayOfWeek
import java.util.*

/**
 * A basic example of how to use week view library.
 */
open class BasicActivity : BaseActivity() {
    var uniqueId: Long = 0
    private fun getUniqueId(): String = uniqueId++.toString()
    private val dayLabels by lazy {
        resources.getStringArray(R.array.custom_weekdays)
    }

    private fun setTodayDecorator(calendar: MaterialCalendarView) {
        val background = resources.getDrawable(R.drawable.bg_calendar_today, theme)
        val textAppearance = TextAppearanceSpan(this, R.style.TodayStyle)
        val todayDecorator = TodayDecorator(CalendarDay.today(), textAppearance, background)
        calendar.addDecorator(todayDecorator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendar1.selectionColor = Color.parseColor("#e6f5fa")
        calendar1.topbarVisible = false
        calendar1.setHeaderTextAppearance(R.style.HeaderStyle)
        calendar1.setWeekDayLabels(dayLabels)
        setTodayDecorator(calendar1)

        calendar1.setWeekDayFormatter { dayOfWeek ->
            val daySpan = SpannableString(dayLabels[dayOfWeek.ordinal])
            when (dayOfWeek) {
                DayOfWeek.SATURDAY -> {
                    daySpan.setSpan(ForegroundColorSpan(Color.RED), 0, daySpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                DayOfWeek.SUNDAY -> {
                    daySpan.setSpan(ForegroundColorSpan(Color.BLUE), 0, daySpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                else -> {
                    daySpan.setSpan(ForegroundColorSpan(Color.BLACK), 0, daySpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
            daySpan
        }

        calendar1.setOnMonthChangedListener { _, date ->
            tv_month.text = "${date.year}年${date.month}月"
        }
        calendar2.topbarVisible = false
        calendar2.setOnMonthChangedListener { _, date ->
            tv_month.text = "${date.year}年${date.month}月"
        }

        tv_month.setOnClickListener {
            if (calendar1.visibility == View.VISIBLE) {
                calendar1.visibility = View.GONE
                calendar2.visibility = View.VISIBLE
            } else {
                calendar1.visibility = View.VISIBLE
                calendar2.visibility = View.GONE
            }
        }

        weekView.eventBorderWidth = 2f
        weekView.isShowNowLine = true
        weekView.showColumnsDayTitle = false
        weekView.weekDayHeaderRowPaddingTop = 0f
        weekView.weekDayHeaderRowPaddingBottom = 0f
        /*supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayHomeAsUpEnabled(true)
            title = ""
            elevation = 0f
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }*/
    }

    inner class TodayDecorator(private val today: CalendarDay,
                               private val textAppearanceSpan: TextAppearanceSpan,
                               private val background: Drawable
    ) : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return day != null && day == today
        }

        override fun decorate(view: DayViewFacade?) {
            view?.setBackgroundDrawable(background)
            view?.addSpan(textAppearanceSpan)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onMonthChange(newYear: Int, newMonth: Int): MutableList<out WeekViewEvent>? {
        // Populate the week view with some events.
        val events = ArrayList<WeekViewEvent>()

        var startTime = Calendar.getInstance()
        startTime.set(2020, 8, 4, 3, 0)
        var endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 4, 4, 0)
        var event = WeekViewEvent(getUniqueId(), "YYZZ", startTime, endTime)
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.location = "YYY -> ZZZ"
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 4, 2, 0)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 4, 3, 30)
        event = WeekViewEvent(getUniqueId(), "AABB", startTime, endTime)
        event.location = "AAA -> BBB"
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 4, 1, 0)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 4, 4, 0)
        event = WeekViewEvent(getUniqueId(), "CCDD", startTime, endTime)
        event.location = "CCC -> DDD"
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 4, 2, 30)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 4, 4, 30)
        event = WeekViewEvent(getUniqueId(), "EEFF", startTime, endTime)
        event.location = "EEE -> FFF"
        event.backgroundColor = ContextCompat.getColor(this, R.color.event_color_07)
        event.borderColor = ContextCompat.getColor(this, R.color.border_event_color_07)
        events.add(event)

        startTime = Calendar.getInstance()
        startTime.set(2020, 8, 4, 6, 0)
        endTime = startTime.clone() as Calendar
        endTime.set(2020, 8, 4, 9, 30)
        event = WeekViewEvent(getUniqueId(), "GGHH", startTime, endTime)
        event.location = "GGG -> HHH"
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