package rs.magus.snaga.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import rs.magus.snaga.R
import rs.magus.snaga.databinding.FragmentHomeBinding
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity
import rs.magus.snaga.ui.home.adapters.ExerciseSetAdapter
import java.time.LocalDateTime
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel
  private var _autocompleteAdapter: ArrayAdapter<ExerciseData>? = null
  private var _binding: FragmentHomeBinding? = null
  private var _exerciseSetAdapter: ExerciseSetAdapter? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  private val autocompleteAdapter get() = _autocompleteAdapter!!
  private val exerciseSetAdapter get() = _exerciseSetAdapter!!

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    homeViewModel =
      ViewModelProvider(this).get(HomeViewModel::class.java)
    _exerciseSetAdapter =
      ExerciseSetAdapter(viewModel = homeViewModel, lifecycleOwner = viewLifecycleOwner)
    _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
    binding.homeViewModel = homeViewModel
    binding.lifecycleOwner = viewLifecycleOwner
    homeViewModel.selectedExercise.observe(viewLifecycleOwner, { data ->
      Log.d("NIKOLA", "NOTIFY")
      binding.lExerciseSetsReps.npWeight.value = data.defaultWeight.roundToInt()
      exerciseSetAdapter.bindData(data)
    })

    binding.rvNumberPickers.adapter = exerciseSetAdapter
    binding.rvNumberPickers.layoutManager = LinearLayoutManager(context)
    binding.ibSplit.setOnClickListener {
      binding.ibSplit.visibility = View.GONE
      binding.rvNumberPickers.visibility = View.VISIBLE
      binding.lExerciseSetsReps.root.visibility = View.GONE
    }
    binding.bSubmit.setOnClickListener {
      handleSubmitPressed()

    }
    return binding.root
  }

  private fun handleSubmitPressed() {
    if (homeViewModel.selectedExercise.value != null) {
      homeViewModel.viewModelScope.launch {
        homeViewModel.insertExerciseLog(
          ExerciseLogEntity(
            homeViewModel.selectedExercise.value!!.id,
            binding.lExerciseSetsReps.npSets.value, binding.lExerciseSetsReps.npReps.value,
            binding.lExerciseSetsReps.npWeight.value.toDouble(), LocalDateTime.now().toString()
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


  }

  private fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long): Unit {
    if (autocompleteAdapter.getItem(position) != null) {
      homeViewModel.selectedExercise.postValue(autocompleteAdapter.getItem(position)!!)

    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}