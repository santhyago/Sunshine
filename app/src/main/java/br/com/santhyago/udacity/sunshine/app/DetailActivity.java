package br.com.santhyago.udacity.sunshine.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;


public class DetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment())
					.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_forecast_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private static final String LOG_TAG = PlaceholderFragment.class.getSimpleName();

		private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
		private String mForecastStr;
		private ShareActionProvider mShareActionProvider;

		public PlaceholderFragment() {
			setHasOptionsMenu(true);
		}

		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			inflater.inflate(R.menu.detail_fragment, menu);

			MenuItem item = menu.findItem(R.id.action_share);

			mShareActionProvider = (ShareActionProvider) item.getActionProvider();

			if (mShareActionProvider != null) {
				mShareActionProvider.setShareIntent(createSharedForecastIntent());
			} else
				Log.d(LOG_TAG, "Share Action Provider is null?");
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			int id = item.getItemId();
			if (id == R.id.action_share) {

			}
			return super.onOptionsItemSelected(item);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

			Intent intent = getActivity().getIntent();
			mForecastStr = intent.getExtras().getString(Intent.EXTRA_TEXT);
			TextView detailText = (TextView) rootView.findViewById(R.id.detail_text);
			detailText.setText(mForecastStr);

			createSharedForecastIntent();
			return rootView;
		}

		private Intent createSharedForecastIntent() {
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
			shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + FORECAST_SHARE_HASHTAG);
			shareIntent.setType("text/plain");
			return shareIntent;
		}
	}
}
