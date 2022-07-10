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
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MovieFragment : Fragment() {

    var movies: List<Movie>? = null
    private lateinit var movieAdapter: MovieAdapter

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        rv_movie_list.layoutManager = LinearLayoutManager(activity)
        rv_movie_list.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rv_movie_list.adapter = MovieAdapter(movies, object : MovieAdapter.OnAdapterListener {
                override fun onClick(result: Movie) {
                    val intent = Intent(activity, DetailMovieActivity::class.java)

                    intent.putExtra(DetailMovieActivity.EXTRA_DATA, result)
                    startActivity(intent)
                }
            })
        }
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(arrayListOf(), object : MovieAdapter.OnAdapterListener {
            override fun onClick(result: Movie) {
                val intent = Intent(activity, DetailMovieActivity::class.java)

                intent.putExtra(DetailMovieActivity.EXTRA_DATA, result)
                startActivity(intent)
            }
        })

        rv_movie_list.apply { layoutManager = LinearLayoutManager(context)
        adapter = movieAdapter
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }
}
