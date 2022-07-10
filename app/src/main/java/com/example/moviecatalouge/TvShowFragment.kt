package com.example.moviecatalouge

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movie_fragment.*
import kotlinx.android.synthetic.main.tv_show_fragment.*
import retrofit2.Call
import retrofit2.Response

class TvShowFragment : Fragment() {

    var tv: List<TvShow>? = null
    private lateinit var TvShowAdapter: TvShowAdapter

    companion object {
        fun newInstance() = TvShowFragment()
    }

    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)

        rv_tv_list.layoutManager = LinearLayoutManager(activity)
        rv_tv_list.setHasFixedSize(true)
        getTvData { tv: List<TvShow> ->
            rv_tv_list.adapter = TvShowAdapter(tv, object : TvShowAdapter.OnAdapterListener {
                override fun onClick(result: TvShow) {
                    val intent = Intent(activity, DetailTvActivity::class.java)

                    intent.putExtra(DetailTvActivity.EXTRA_DATA, result)
                    startActivity(intent)
                }
            })
        }
    }

    private fun setupRecyclerView() {
        TvShowAdapter = TvShowAdapter(arrayListOf(), object : TvShowAdapter.OnAdapterListener {
            override fun onClick(result: TvShow) {
                val intent = Intent(activity, DetailMovieActivity::class.java)

                intent.putExtra(DetailTvActivity.EXTRA_DATA, result)
                startActivity(intent)
            }
        })

        rv_tv_list.apply { layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter
        }
    }

    private fun getTvData(callback: (List<TvShow>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getTvList().enqueue(object : retrofit2.Callback<TvResponse> {
            override fun onFailure(call: Call<TvResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                return callback(response.body()!!.tv)
            }

        })
    }
}
