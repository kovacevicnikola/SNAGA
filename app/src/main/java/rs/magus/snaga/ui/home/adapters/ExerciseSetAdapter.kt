package rs.magus.snaga.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import rs.magus.snaga.R
import rs.magus.snaga.databinding.ListItemExerciseSetBinding
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.ui.home.HomeViewModel

class ExerciseSetAdapter(
    private val viewModel: HomeViewModel,
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<ExerciseSetAdapter.ExerciseSetViewHolder>() {
    var selectedExercise: ExerciseData? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseSetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemExerciseSetBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_item_exercise_set, parent, false)
        binding.lifecycleOwner = lifecycleOwner
        return ExerciseSetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseSetViewHolder, position: Int) {
        holder.bind(viewModel.selectedExercise.value)
    }

    fun bindData(exerciseData: ExerciseData?) {
        selectedExercise = exerciseData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("NIKOLA", "" + selectedExercise?.defaultReps)
        return selectedExercise?.defaultReps ?: 1
    }

    inner class ExerciseSetViewHolder(val binding: ListItemExerciseSetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exerciseData: ExerciseData?) {
            binding.selectedExercise = exerciseData
            binding.npSets.value = 1
        }

    }
}