package com.progtech.monitoringstockexchange.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

class CardAdapter(private val context: Context, private val viewModel: StocksViewModel) :
    RecyclerView.Adapter<CardAdapter.CardVH>() {

    private val cards = ArrayList<Card>()

    class CardVH(val binding: StockCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StockCardBinding.inflate(inflater, parent, false)
        return CardVH(binding)
    }

    fun putImage(url: String, parent: View, imageView: ImageView) {
        Glide.with(context).clear(parent)
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    override fun onBindViewHolder(holder: CardVH, position: Int) {
        val card = cards[position]
        val view = holder.itemView

        holder.binding.card = card
        holder.binding.isOdd = (position % 2) == 0

        card.image?.let {
            putImage(it, view, holder.binding.ivBrandIcon)
        }

        card.companyName?.let {
            holder.binding.tvFullName.text = it
        }

        viewModel.getCompanyProfile(card.symbol).observeForever { profile ->
            card.companyName = profile.name
            holder.binding.tvFullName.text = profile.name

            card.image = profile.logo
            card.image?.let {
                putImage(it, view, holder.binding.ivBrandIcon)
            }
        }

        fillPrice(card, holder)

        viewModel.getQuote(card.symbol).observeForever {
            card.price = String.format(
                Locale.getDefault(), "$%.2f", it.c
            )

            val delta = it.c - it.pc
            var deltaPercents = 0.0
            if (it.pc != 0.0) deltaPercents = delta / it.pc * 100.0
            card.deltaPrice = String.format(
                Locale.getDefault(), "$%.2f (%.2f%%)", delta, deltaPercents
            )

            card.deltaPriceSign = delta > 0

            fillPrice(card, holder)
        }
    }

    fun fillPrice(card: Card, holder: CardVH) {
        card.price?.let {
            holder.binding.tvPrice.text = it
        }

        card.deltaPrice?.let {
            holder.binding.tvDeltaPrice.text = it
        }

        if (card.deltaPriceSign)
            holder.binding.tvDeltaPrice.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.green)
            )
        else
            holder.binding.tvDeltaPrice.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.red)
            )
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