package rs.magus.snaga.ui.setup.exerciseDay

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rs.magus.snaga.databinding.ListItemExerciseDayExerciseBinding

class ExerciseDayExerciseRecyclerViewAdapter :
    RecyclerView.Adapter<ExerciseDayExerciseRecyclerViewAdapter.ExerciseDayExerciseViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseDayExerciseViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ExerciseDayExerciseViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 0 //todo
    }

    inner class ExerciseDayExerciseViewHolder(val binding: ListItemExerciseDayExerciseBinding) :
        RecyclerView.ViewHolder(binding.root)
}