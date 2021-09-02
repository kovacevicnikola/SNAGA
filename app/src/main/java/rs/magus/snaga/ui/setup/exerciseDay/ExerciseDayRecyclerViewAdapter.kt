package rs.magus.snaga.ui.setup.exerciseDay

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
                MutableLiveData(""), ArrayList(), ArrayList()
            )
        )
    )
    private var selectedItem: Int = 0
    private val adapter: ExerciseDayExerciseRecyclerViewAdapter =
        ExerciseDayExerciseRecyclerViewAdapter(exercises)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseDayViewHolder {

        return ExerciseDayViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_exercise_day,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ExerciseDayViewHolder, position: Int) {
        holder.binding.exerciseDayData = exerciseDataList[position]
        holder.binding.rvExercises.adapter = adapter
        adapter.bindData(exerciseDataList[position].displayExercises)
        if (itemCount == 1 && position == 0) holder.binding.bRemove.visibility = View.GONE
        else holder.binding.bRemove.visibility = View.VISIBLE
        holder.binding.bRemove.setOnClickListener {
            exerciseDataList.removeAt(position)
            notifyItemRemoved(position)
            if (itemCount == 1) notifyItemChanged(0)
        }
        if (selectedItem != position) {
            //holder.binding.root.setBackgroundColor(Color.RED)
            holder.binding.rvExercises.visibility = View.GONE
            holder.binding.bAddExercise.visibility = View.GONE
            holder.binding.etLayoutExerciseDayName.visibility = View.GONE
            holder.binding.tvExerciseDayName.visibility = View.VISIBLE
            holder.binding.tvExerciseDayName.text = holder.binding.etExerciseDayName.text
        } else {
            // holder.binding.root.setBackgroundColor(Color.GREEN)
            holder.binding.rvExercises.visibility = View.VISIBLE
            holder.binding.bAddExercise.visibility = View.VISIBLE
            holder.binding.etLayoutExerciseDayName.visibility = View.VISIBLE
            holder.binding.tvExerciseDayName.visibility = View.GONE
            holder.binding.etExerciseDayName.requestFocus()

        }
        holder.binding.root.setOnClickListener {
            setSelection(position)

        }
        holder.binding.bAddExercise.setOnClickListener { adapter.addExercise() }

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
        exerciseDataList.add(NewExerciseDayData(MutableLiveData(""), ArrayList(), ArrayList()))
        selectedItem = exerciseDataList.size - 1
        notifyItemChanged(tmp)
        notifyItemInserted(exerciseDataList.size - 1)
    }

    inner class ExerciseDayViewHolder(
        val binding: ListItemExerciseDayBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = binding.root.findViewTreeLifecycleOwner()
            binding.rvExercises.layoutManager = LinearLayoutManager(binding.root.context)


        }

    }
}