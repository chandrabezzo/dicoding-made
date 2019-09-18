package com.bezzo.moviecatalogue.features.movie

import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.core.base.BasePresenter
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.SchedulerProviderUtil
import com.bezzo.moviecatalogue.R
import io.reactivex.disposables.CompositeDisposable

class MoviePresenter<V: MovieViewContract>(
    sessionHelper: SessionHelper,
    schedulerProvider: SchedulerProviderUtil,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V>(sessionHelper, schedulerProvider, compositeDisposable), MoviePresenterContract<V> {

    override fun getMovie() {
        val movies = ArrayList<Movie>()

        val movie1 = Movie(
            1,
            "A Star Is Born",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling " +
                    "artist Ally. She has just about given up on her dream to make it big as a singer — " +
                    "until Jack coaxes her into the spotlight. But even as Ally's career takes off, " +
                    "the personal side of their relationship is breaking down, as Jack fights an " +
                    "ongoing battle with his own internal demons.",
            "2018",
            "2 Hours 15 Minute",
            "Drama, Romance, Music",
            "75%",
            R.drawable.poster_a_star
        )

        val movie2 = Movie(
            2,
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater " +
                    "kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, " +
                    "Orm plans to conquer the remaining oceanic people and then the surface world. " +
                    "Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother " +
                    "and true heir to the throne.",
            "2018",
            "2 Hours 24 Minute",
            "Action, Adventure, Fantasy",
            "68%",
            R.drawable.poster_aquaman
        )

        val movie3 = Movie(
            3,
            "Avengers: Infinity War",
            "As the Avengers and their allies have continued to protect the world from " +
                    "threats too large for any one hero to handle, a new danger has emerged from " +
                    "the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to " +
                    "collect all six Infinity Stones, artifacts of unimaginable power, and use them " +
                    "to inflict his twisted will on all of reality. Everything the Avengers have fought " +
                    "for has led up to this moment - the fate of Earth and existence itself has never " +
                    "been more uncertain.",
            "2018",
            "2 Hours 29 Minute",
            "Adventure, Action, Science Fiction",
            "83%",
            R.drawable.poster_avengerinfinity
        )

        val movie4 = Movie(
            4,
            "Bird Box",
            "Five years after an ominous unseen presence drives most of society to suicide, " +
                    "a survivor and her two children make a desperate bid to reach safety.",
            "2018",
            "2 Hours 4 Minute",
            "Thriller, Drama",
            "70%",
            R.drawable.poster_birdbox
        )

        val movie5 = Movie(
            5,
            "Bohemian",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass " +
                    "guitarist John Deacon take the music world by storm when they form the rock " +
                    "'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's " +
                    "increasingly wild lifestyle starts to spiral out of control, Queen soon faces " +
                    "its greatest challenge yet – finding a way to keep the band together amid the " +
                    "success and excess.",
            "2018",
            "2 Hours 15 Minutes",
            "Drama, Music",
            "81%",
            R.drawable.poster_bohemian
        )

        val movie6 = Movie(
            6,
            "Bumblebee",
            "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small" +
                    " Californian beach town. Charlie, on the cusp of turning 18 and trying to find " +
                    "her place in the world, discovers Bumblebee, battle-scarred and broken. When " +
                    "Charlie revives him, she quickly learns this is no ordinary yellow VW bug.",
            "2018",
            "1 Hours 54 Minutes",
            "Action, Adventure, Science Fiction",
            "65%",
            R.drawable.poster_bumblebee
        )

        val movie7 = Movie(
            7,
            "Creed II",
            "Between personal obligations and training for his next big fight against an " +
                    "opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "2018",
            "2 Hours 10 Minute",
            "Drama",
            "67%",
            R.drawable.poster_creed
        )

        val movie8 = Movie(
            8,
            "Once Upon a Deadpool",
            "A kidnapped Fred Savage is forced to endure Deadpool's PG-13 rendition of " +
                    "Deadpool 2 as a Princess Bride-esque story that's full of magic, wonder & zero F's.",
            "2018",
            "1 Hours 57 Minute",
            "Comedy, Action",
            "69%",
            R.drawable.poster_deadpool
        )

        val movie9 = Movie(
            9,
            "Dragon Ball Super: Broly",
            "Earth is peaceful following the Tournament of Power. Realizing that the universes " +
                    "still hold many more strong people yet to see, Goku spends all his days training " +
                    "to reach even greater heights. Then one day, Goku and Vegeta are faced by a Saiyan " +
                    "called 'Broly' who they've never seen before. The Saiyans were supposed to have been " +
                    "almost completely wiped out in the destruction of Planet Vegeta, so what's this " +
                    "one doing on Earth? This encounter between the three Saiyans who have followed " +
                    "completely different destinies turns into a stupendous battle, with even Frieza" +
                    " (back from Hell) getting caught up in the mix.",
            "2018",
            "1 Hours 41 Minute",
            "Action, Animation, Fantasy, Adventure, Comedy, Science Fiction",
            "75%",
            R.drawable.poster_dragonball
        )

        val movie10 = Movie(
            10,
            "Glass",
            "In a series of escalating encounters, former security guard David Dunn uses " +
                    "his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who " +
                    "has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price " +
                    "emerges as an orchestrator who holds secrets critical to both men.",
            "2019",
            "2 Hours 9 Minute",
            "Thriller, Drama, Science Fiction",
            "65%",
            R.drawable.poster_glass
        )

        val movie11 = Movie(
            11,
            "Hunter Killer",
            "Captain Glass of the USS Arkansas discovers that a coup d'état is taking place " +
                    "in Russia, so he and his crew join an elite group working on the ground to prevent a war.",
            "2018",
            "2 Hours 2 Minute",
            "Action, Thriller",
            "63%",
            R.drawable.poster_hunterkiller
        )

        val movie12 = Movie(
            12,
            "Preman Pensiun",
            "After three years, the business of Muslihat (Epi Kusnandar), who has retired " +
                    "as a thug, has a problem. Sales decline. Muslihat also faces new problems when " +
                    "Safira (Safira Maharani), her only daughter, grows up in adolescence and begins " +
                    "to be visited by boys. A bigger problem: frictions between his former subordinates.",
            "2019",
            "1 Hour 34 Minute",
            "Comedy, Drama",
            "65%",
            R.drawable.poster_preman
        )

        movies.add(movie1)
        movies.add(movie2)
        movies.add(movie3)
        movies.add(movie4)
        movies.add(movie5)
        movies.add(movie6)
        movies.add(movie7)
        movies.add(movie8)
        movies.add(movie9)
        movies.add(movie10)
        movies.add(movie11)
        movies.add(movie12)

        view?.showMovies(movies)
    }
}