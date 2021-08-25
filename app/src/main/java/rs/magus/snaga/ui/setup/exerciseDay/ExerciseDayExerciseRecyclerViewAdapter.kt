package rs.magus.snaga.ui.setup.exerciseDay

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import rs.magus.snaga.databinding.ListItemExerciseDayExerciseBinding
import rs.magus.snaga.pojo.models.ExerciseData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

class ExerciseDayExerciseRecyclerViewAdapter(
    private val exercises : List<ExerciseEntity>
) :
    RecyclerView.Adapter<ExerciseDayExerciseRecyclerViewAdapter.ExerciseDayExerciseViewHolder>() {
    val exerciseList: MutableList<ExerciseData> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseDayExerciseViewHolder {
        return ExerciseDayExerciseViewHolder(ListItemExerciseDayExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun onBindViewHolder(holder: ExerciseDayExerciseViewHolder, position: Int) {
        holder.binding.etAutocomplete.setAdapter(ArrayAdapter(holder.binding.root.context,
        android.R.layout.simple_dropdown_item_1line, exercises))

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    inner class ExerciseDayExerciseViewHolder(val binding: ListItemExerciseDayExerciseBinding) :
        RecyclerView.ViewHolder(binding.root)
}