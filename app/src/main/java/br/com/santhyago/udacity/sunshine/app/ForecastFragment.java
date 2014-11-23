package br.com.santhyago.udacity.sunshine.app;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by san on 11/18/14.
 */

public class ForecastFragment extends Fragment {
	ArrayAdapter<String> mForecastAdapter;
	Context context;

	public ForecastFragment() {
	}

	@Override
	public void onStart() {
		super.onStart();
		updateWeather();
	}

	private void updateWeather() {
		String location = Utility.getPreferredLocation(getActivity());
		new FetchWeatherTask(getActivity(), mForecastAdapter).execute(location);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//		ArrayList<String> weekForecast = new ArrayList<String> (Arrays.asList(getResources().getStringArray(R.array.forecast_data)));
		ArrayList<String> weekForecast = new ArrayList<String> ();

		mForecastAdapter = new ArrayAdapter<String>(context, R.layout.list_item_forecast, R.id.list_item_forecast_textview, weekForecast);

		ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
		listView.setAdapter(mForecastAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String forecast = mForecastAdapter.getItem(position);
				Intent intent = new Intent(context, DetailActivity.class)
						.putExtra(Intent.EXTRA_TEXT, forecast);
				startActivity(intent);
			}
		});

        return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.forecast_fragment, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_refresh) {
			updateWeather();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}