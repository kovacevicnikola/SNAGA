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
import rs.magus.snaga.repository.datasources.db.entities.ExerciseLogEntity
import rs.magus.snaga.ui.home.ExerciseLoggingViewModel

class ExerciseSetAdapter(
    private val viewModel: ExerciseLoggingViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private var splitSets: Boolean
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
    fun swapSelection() {
        splitSets = !splitSets
        if (splitSets) notifyItemRangeInserted(1, itemCount)
        else notifyItemRangeRemoved(1, itemCount)
    }

    override fun getItemCount(): Int {
        if (splitSets) return selectedExercise?.repsSetsAndWeight?.size?:1
        return selectedExercise?.defaultReps?:1
    }

    fun getSets(): List<ExerciseLogEntity> {
        return ArrayList() //todo
    }

    inner class ExerciseSetViewHolder(val binding: ListItemExerciseSetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exerciseData: ExerciseData?) {
            binding.selectedExercise = exerciseData
            binding.npSets.value = 1
        }

    }
}