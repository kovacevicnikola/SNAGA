package rs.magus.snaga.ui.setup.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import rs.magus.snaga.databinding.CalendarDayLayoutBinding
import rs.magus.snaga.databinding.FragmentCalendarBinding
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class CalendarSetupFragment : Fragment() {

    private lateinit var calendarViewModel: CalendarViewModel
    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    calendarViewModel =
            ViewModelProvider(this).get(CalendarViewModel::class.java)

    _binding = FragmentCalendarBinding.inflate(inflater, container, false)
    val root: View = binding.root

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
      // Called only when a new container is needed.
      override fun create(view: View) = DayViewContainer(view)

      // Called every time we need to reuse a container.
      override fun bind(container: DayViewContainer, day: CalendarDay) {
        container.textView.text = day.date.dayOfMonth.toString()
      }
    }
    val currentMonth = YearMonth.now()

    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    binding.calendarView.setup(currentMonth, currentMonth, firstDayOfWeek)
    binding.calendarView.scrollToMonth(currentMonth)
  }

  override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  class DayViewContainer(view: View) : ViewContainer(view) {

    // With ViewBinding
     val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
  }
}