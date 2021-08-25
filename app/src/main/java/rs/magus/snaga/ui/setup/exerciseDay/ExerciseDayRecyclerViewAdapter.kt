package rs.magus.snaga.ui.setup.exerciseDay

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rs.magus.snaga.R
import rs.magus.snaga.databinding.ListItemExerciseDayBinding
import rs.magus.snaga.pojo.models.NewExerciseDayData
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

class ExerciseDayRecyclerViewAdapter(
    private val exercises : List<ExerciseEntity>
) :
    RecyclerView.Adapter<ExerciseDayRecyclerViewAdapter.ExerciseDayViewHolder>() {
    var exerciseDataList: MutableList<NewExerciseDayData> = ArrayList(
        mutableListOf(
            NewExerciseDayData(
                MutableLiveData(""), ArrayList()
            )
        )
    )
    private var selectedItem: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseDayViewHolder {

        return ExerciseDayViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_exercise_day,
                parent,
                false
            ),
            ExerciseDayExerciseRecyclerViewAdapter(exercises)
        )

    }

    override fun onBindViewHolder(holder: ExerciseDayViewHolder, position: Int) {
        holder.binding.exerciseDayData = exerciseDataList[position]

        if (selectedItem != position) {
            holder.binding.root.setBackgroundColor(Color.RED)
            holder.binding.rvExercises.visibility = View.GONE
            holder.binding.etLayoutExerciseDayName.visibility = View.GONE
            holder.binding.tvExerciseDayName.visibility = View.VISIBLE
            holder.binding.tvExerciseDayName.text = holder.binding.etExerciseDayName.text
        } else {
            holder.binding.root.setBackgroundColor(Color.GREEN)
            holder.binding.rvExercises.visibility = View.VISIBLE
            holder.binding.etLayoutExerciseDayName.visibility = View.VISIBLE
            holder.binding.tvExerciseDayName.visibility = View.GONE
            holder.binding.etExerciseDayName.requestFocus()

        }
        holder.binding.root.setOnClickListener {
            setSelection(position)

        }
        holder.binding.

    }

    private fun setSelection(position: Int) {
        if (selectedItem == position) return
        val tmp: Int = selectedItem
        selectedItem = position
        notifyItemChanged(tmp)
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return exerciseDataList.size
    }

    fun addView() {
        val tmp = selectedItem
        exerciseDataList.add(NewExerciseDayData(MutableLiveData(""), ArrayList()))
        selectedItem = exerciseDataList.size - 1
        notifyItemChanged(tmp)
        notifyItemInserted(exerciseDataList.size - 1)
    }

    inner class ExerciseDayViewHolder(
        val binding: ListItemExerciseDayBinding,
        val adapter: ExerciseDayExerciseRecyclerViewAdapter,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = binding.root.findViewTreeLifecycleOwner()
            binding.rvExercises.adapter = adapter
            binding.rvExercises.layoutManager = LinearLayoutManager(binding.root.context)


        }

    }
}