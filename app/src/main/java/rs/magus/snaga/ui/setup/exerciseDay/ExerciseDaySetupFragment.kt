package rs.magus.snaga.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rs.magus.snaga.databinding.FragmentExerciseDaySetupBinding
import rs.magus.snaga.ui.setup.exerciseDay.ExerciseDayRecyclerViewAdapter

class ExerciseDaySetupFragment : Fragment() {

    private lateinit var setupViewModel: SetupViewModel
    private var _binding: FragmentExerciseDaySetupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter: ExerciseDayRecyclerViewAdapter = ExerciseDayRecyclerViewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setupViewModel =
            ViewModelProvider(this).get(SetupViewModel::class.java)

        _binding = FragmentExerciseDaySetupBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvExerciseDays.layoutManager = LinearLayoutManager(context)
        binding.rvExerciseDays.adapter = adapter
        binding.bAdd.setOnClickListener {
            if (!binding.rvExerciseDays.isComputingLayout) adapter.addView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}