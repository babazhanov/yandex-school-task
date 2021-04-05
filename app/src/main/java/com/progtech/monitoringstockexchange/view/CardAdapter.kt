package com.progtech.monitoringstockexchange.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.progtech.monitoringstockexchange.R
import com.progtech.monitoringstockexchange.databinding.StockCardBinding
import com.progtech.monitoringstockexchange.model.Card
import com.progtech.monitoringstockexchange.viewmodel.StocksViewModel
import java.util.*
import kotlin.collections.ArrayList

class CardAdapter(private val context: Context, private val viewModel: StocksViewModel)
    : RecyclerView.Adapter<CardAdapter.CardVH>() {

    private val cards = ArrayList<Card>()

    class CardVH(val binding: StockCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StockCardBinding.inflate(inflater, parent, false)
        return CardVH(binding)
    }

    override fun onBindViewHolder(holder: CardVH, position: Int) {
        val card = cards[position]
        val view = holder.itemView

        holder.binding.card = card
        holder.binding.isOdd = (position % 2) == 0

        viewModel.getCompanyProfile(card.symbol).observeForever {
            holder.binding.tvFullName.text = it.name

            Glide.with(context).clear(view)
            Glide.with(context)
                .load(it.logo)
                .placeholder(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.binding.ivBrandIcon)
        }

        viewModel.getQuote(card.symbol).observeForever {
            holder.binding.tvPrice.text = String.format(
                Locale.getDefault(), "$%.2f", it.c
            )

            val delta = it.c - it.pc
            var deltaPercents = 0.0
            if (it.pc != 0.0) deltaPercents = delta / it.pc * 100.0
            holder.binding.tvDeltaPrice.text = String.format(
                Locale.getDefault(), "$%.2f (%.2f%%)", delta, deltaPercents
            )

            if (delta < 0)
                holder.binding.tvDeltaPrice.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.red)
                )
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun addCards(data: List<Card>, isRefresh: Boolean) {
        if (isRefresh)
            cards.clear()

        cards.addAll(data)
        notifyDataSetChanged()
    }
}