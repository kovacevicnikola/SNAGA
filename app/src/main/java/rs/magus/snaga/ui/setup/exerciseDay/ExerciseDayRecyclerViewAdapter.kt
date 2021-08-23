package rs.magus.snaga.ui.setup.exerciseDay

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rs.magus.snaga.databinding.ListItemExerciseDayBinding
import rs.magus.snaga.repository.datasources.db.entities.ExerciseEntity

class ExerciseDayRecyclerViewAdapter :
    RecyclerView.Adapter<ExerciseDayRecyclerViewAdapter.ExerciseDayViewHolder>() {
    var size: Int = 1
    var exerciseList: List<ExerciseEntity> = ArrayList()
    var selectedItem: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseDayViewHolder {
        return ExerciseDayViewHolder(
            ListItemExerciseDayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), ExerciseDayExerciseRecyclerViewAdapter()
        )
    }

    override fun onBindViewHolder(holder: ExerciseDayViewHolder, position: Int) {
        if (!holder.bound) {
            holder.bound = true
            holder.binding.etExerciseDayName.requestFocus()
        }
        if (selectedItem != position) {
            holder.binding.root.setBackgroundColor(Color.RED)
            holder.binding.rvExercises.visibility = View.GONE
        } else {
            holder.binding.root.setBackgroundColor(Color.GREEN)
            holder.binding.rvExercises.visibility = View.VISIBLE
        }
        holder.binding.root.setOnClickListener {
            holder.binding.etExerciseDayName.requestFocus()//TODO
            // setSelection(position)
        }

        holder.binding.etExerciseDayName.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus -> if (hasFocus) setSelection(position) }

    }

    private fun setSelection(position: Int) {
        if (selectedItem == position) return
        val tmp: Int = selectedItem
        selectedItem = position
        notifyItemChanged(tmp)
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return size
    }

    fun addView() {
        val tmp = selectedItem
        selectedItem = size++
        notifyItemChanged(tmp)
        notifyItemInserted(size - 1)
    }

    inner class ExerciseDayViewHolder(
        val binding: ListItemExerciseDayBinding,
        val adapter: ExerciseDayExerciseRecyclerViewAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        var bound = false

        init {
            binding.rvExercises.adapter = adapter
            binding.rvExercises.layoutManager = LinearLayoutManager(binding.root.context)


        }

    }
}