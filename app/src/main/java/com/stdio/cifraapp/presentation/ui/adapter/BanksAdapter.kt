package student.testing.system.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stdio.cifraapp.databinding.ItemBankBinding
import com.stdio.cifraapp.domain.models.Bank

class BanksAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<BanksAdapter.CourseViewHolder>() {

    private var dataList = emptyList<Bank>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemBankBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    fun setDataList(dataList: List<Bank>) {
        this.dataList = dataList
        notifyItemRangeChanged(0, dataList.size)
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        with(holder) {
            val bank = dataList[position]
            binding.tvName.text = bank.bankName
            binding.btnConnect.setOnClickListener {
                listener.invoke(bank.bank)
            }
        }
    }

    inner class CourseViewHolder(val binding: ItemBankBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ClickListener {
        fun onClick(bank: Bank)
    }
}