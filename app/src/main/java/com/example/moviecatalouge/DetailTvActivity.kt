package com.example.moviecatalouge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    var tv: TvShow? = null

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)

        tv = intent.getParcelableExtra(EXTRA_DATA)

        Tv_title.text = tv?.name
        Tv_status.text = tv?.first_air_date
        Tv_rating.text = tv?.vote_average
        Tv_overview.text = tv?.overview
        Glide.with(img_poster_tv).load(IMAGE_BASE + tv!!.poster_path).into(img_poster_tv)
    }
}