package rs.magus.snaga.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.magus.snaga.databinding.FragmentHomeBinding
import rs.magus.snaga.db.ExerciseLogEntity
import java.time.LocalDateTime

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel
  private var _autocompleteAdapter: ArrayAdapter<String>? = null
private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private val autocompleteAdapter get() = _autocompleteAdapter!!
  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    homeViewModel =
      ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    _autocompleteAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, homeViewModel.exerciseList)
    binding.autoComplete.setAdapter(autocompleteAdapter)
    binding.npReps.minValue = 1
    binding.npReps.maxValue = 100
    binding.npSets.minValue = 1
    binding.npSets.maxValue = 12
    binding.bSubmit.setOnClickListener {
      homeViewModel.viewModelScope.launch {
        homeViewModel.insertExerciseLog(
          ExerciseLogEntity(
            1, binding.autoComplete.text.toString(),
            binding.npSets.value, binding.npReps.value, LocalDateTime.now().toString()
          )
        )
      }
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}