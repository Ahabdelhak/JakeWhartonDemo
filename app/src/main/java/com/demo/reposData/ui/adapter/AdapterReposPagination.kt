package com.demo.reposData.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.reposData.R
import com.demo.reposData.common.PaginationAdapterCallback
import com.demo.reposData.databinding.ItemLoadingBinding
import com.demo.reposData.databinding.ItemReposBinding
import com.demo.reposData.ui.main.MainFragment
import com.demo.reposData.BR
import com.demo.reposData.data.model.JackwhartonResponseItem

class AdapterReposPagination (private var mActivity: MainFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() ,
    PaginationAdapterCallback {

    private val item: Int = 0
    private val loading: Int = 1
    private var isLoadingAdded: Boolean = false
    private var retryPageLoad: Boolean = false
    private var errorMsg: String? = ""
    private var reposModels: MutableList<JackwhartonResponseItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  if(viewType == item){
            val binding: ItemReposBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repos, parent, false)
            TopReposVH(binding)
        }else{
            val binding: ItemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_loading, parent, false)
            LoadingVH(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = reposModels[position]
        if(getItemViewType(position) == item){
            val myOrderVH: TopReposVH = holder as TopReposVH
            //myOrderVH.itemRowBinding.RepoProgress.visibility = View.VISIBLE
            myOrderVH.bind(model)
        }else{
            val loadingVH: LoadingVH = holder as LoadingVH
            if (retryPageLoad) {
                loadingVH.itemRowBinding.loadmoreErrorlayout.visibility = View.VISIBLE
                loadingVH.itemRowBinding.loadmoreProgress.visibility = View.GONE

                if(errorMsg != null) loadingVH.itemRowBinding.loadmoreErrortxt.text = errorMsg
                else loadingVH.itemRowBinding.loadmoreErrortxt.text = mActivity.getString(R.string.error_msg_unknown)

            } else {
                loadingVH.itemRowBinding.loadmoreErrorlayout.visibility = View.GONE
                loadingVH.itemRowBinding.loadmoreProgress.visibility = View.VISIBLE
            }

            loadingVH.itemRowBinding.loadmoreRetry.setOnClickListener{
                showRetry(false, "")
                retryPageLoad()
            }
            loadingVH.itemRowBinding.loadmoreErrorlayout.setOnClickListener{
                showRetry(false, "")
                retryPageLoad()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (reposModels.size > 0) reposModels.size else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0){
            item
        }else {
            if (position == reposModels.size - 1 && isLoadingAdded) {
                loading
            } else {
                item
            }
        }
    }

    override fun retryPageLoad() {
        mActivity.loadNextPage()
    }


    class TopReposVH(binding: ItemReposBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemReposBinding = binding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }

    class LoadingVH(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemLoadingBinding = binding
    }

    fun showRetry(show: Boolean, errorMsg: String) {
        retryPageLoad = show
        notifyItemChanged(reposModels.size - 1)
        this.errorMsg = errorMsg
    }

    fun addAll(Repos: List<JackwhartonResponseItem>) {
        for(Repo in Repos){
            add(Repo)
        }
    }

    fun add(moive: JackwhartonResponseItem) {
        reposModels.add(moive)
        notifyItemInserted(reposModels.size - 1)
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(JackwhartonResponseItem())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position: Int =reposModels.size -1
        val repo: JackwhartonResponseItem = reposModels[position]

        if(repo != null){
            reposModels.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}