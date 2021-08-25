package rs.magus.snaga.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import rs.magus.snaga.databinding.FragmentExerciseDaySetupBinding
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity
import rs.magus.snaga.ui.setup.exerciseDay.ExerciseDayRecyclerViewAdapter

class ExerciseDaySetupFragment : Fragment() {

    private lateinit var setupViewModel: SetupViewModel
    private var _binding: FragmentExerciseDaySetupBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: ExerciseDayRecyclerViewAdapter
    private lateinit var exerciseList: List<ExerciseEntity>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseDaySetupBinding.inflate(inflater, container, false)

        setupViewModel =
            ViewModelProvider(this).get(SetupViewModel::class.java)
        setupViewModel.viewModelScope.launch {
            exerciseList = setupViewModel.getExercises()
        }.invokeOnCompletion {
            adapter = ExerciseDayRecyclerViewAdapter(exerciseList)
            binding.rvExerciseDays.layoutManager = LinearLayoutManager(context)
            binding.rvExerciseDays.adapter = adapter
        }
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bAdd.setOnClickListener {
            if (!binding.rvExerciseDays.isComputingLayout) adapter.addView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}