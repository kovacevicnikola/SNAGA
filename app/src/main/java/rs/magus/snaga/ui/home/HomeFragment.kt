package rs.magus.snaga.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import rs.magus.snaga.databinding.FragmentHomeBinding
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity
import java.time.LocalDateTime
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel
  private var _autocompleteAdapter: ArrayAdapter<ExerciseData>? = null
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

    binding.npReps.minValue = 1
    binding.npReps.maxValue = 100
    binding.npSets.minValue = 1
    binding.npSets.maxValue = 12
    binding.npWeight.minValue = 1
    binding.npWeight.maxValue = 500
    binding.bSubmit.setOnClickListener {
      handleSubmitPressed()

    }
    return binding.root
  }

  private fun handleSubmitPressed() {
    if (homeViewModel.selectedExercise != null) {
      homeViewModel.viewModelScope.launch {
        homeViewModel.insertExerciseLog(
          ExerciseLogEntity(
            homeViewModel.selectedExercise!!.id,
            binding.npSets.value, binding.npReps.value,
            binding.npWeight.value.toDouble(), LocalDateTime.now().toString()
          )
        )
      }
    } else {
      binding.autoComplete.requestFocus()
      binding.autoComplete.error = "You need to fill out this field!"
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    homeViewModel.viewModelScope.launch {
      _autocompleteAdapter = ArrayAdapter(
        requireContext(),
        android.R.layout.simple_dropdown_item_1line,
        homeViewModel.getExercisesFromDB()
      )
    }.invokeOnCompletion { autofillValues() }
  }

  private fun autofillValues() {
    binding.autoComplete.setAdapter(autocompleteAdapter)
    binding.autoComplete.onItemClickListener = AdapterView.OnItemClickListener(this::onItemClick)
    if (homeViewModel.exercises.isNotEmpty()) {
      binding.npReps.value = homeViewModel.exercises.first().defaultReps
      binding.npSets.value = homeViewModel.exercises.first().defaultSets
      binding.npWeight.value = homeViewModel.exercises.first().defaultWeight.roundToInt()

    }
  }

  private fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long): Unit {
    if (autocompleteAdapter.getItem(position) != null) {
      homeViewModel.selectedExercise = autocompleteAdapter.getItem(position)!!
      binding.npWeight.value = autocompleteAdapter.getItem(position)!!.defaultWeight.roundToInt()
      binding.npSets.value = autocompleteAdapter.getItem(position)!!.defaultSets
      binding.npReps.value = autocompleteAdapter.getItem(position)!!.defaultReps
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}