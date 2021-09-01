package rs.magus.snaga.ui.setup.exerciseDay

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import rs.magus.snaga.R
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
        val listBinding: ListItemExerciseDayExerciseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_exercise_day_exercise, parent, false
        )
        listBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ExerciseDayExerciseViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: ExerciseDayExerciseViewHolder, position: Int) {

        holder.binding.etAutocomplete.setAdapter(
            ArrayAdapter(
                holder.binding.root.context,
                android.R.layout.simple_dropdown_item_1line, exercises
            )
        )


        holder.binding.etAutocomplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, listPosition, id ->
                if (parent.getItemAtPosition(listPosition) != null)
                    exerciseList[position].exercise.postValue(parent.getItemAtPosition(listPosition)!! as ExerciseEntity)
            }


    }


    override fun getItemCount(): Int {
        return exerciseList.size
    }

    fun addExercise() {
        exerciseList.add(ExerciseData())
        notifyItemInserted(exerciseList.size - 1)
    }

    inner class ExerciseDayExerciseViewHolder(val binding: ListItemExerciseDayExerciseBinding) :
        RecyclerView.ViewHolder(binding.root)
}