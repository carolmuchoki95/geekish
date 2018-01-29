package googlemaps.com.searchrecyclerview.Lectures;

import com.orm.SugarRecord;

/**
 * Created by Narse on 4/4/2017.
 */

public class Thursday_Model extends SugarRecord {
    public String activity;

    public String time;

    public String venue;

    public Thursday_Model() {
    }

    public Thursday_Model(String activity, String time, String venue) {
        this.activity = activity;
        this.time = time;
        this.venue = venue;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
