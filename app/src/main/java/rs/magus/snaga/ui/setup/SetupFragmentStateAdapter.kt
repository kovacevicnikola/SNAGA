package rs.magus.snaga.ui.setup

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import rs.magus.snaga.ui.setup.calendar.CalendarSetupFragment

class SetupFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) ExerciseDaySetupFragment()
        else CalendarSetupFragment()
    }
}