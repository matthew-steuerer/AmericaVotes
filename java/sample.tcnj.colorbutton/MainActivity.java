package sample.tcnj.colorbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView c_name;
    private TextView c_party;
    private TextView c_votes;
    private ListView c_list;
    private List<Candidate> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        c_name = (TextView)findViewById(R.id.c_name);
        c_party = (TextView)findViewById(R.id.c_party);
        c_votes = (TextView)findViewById(R.id.c_votes);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setTextFromRESTService();
            }
        });

        candidatesAdaptor adapter = new candidatesAdaptor(this, candidates);
        c_list = (ListView) findViewById(R.id.c_list);

        c_list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setTextFromRESTService()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.justindilks.com/candidates.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject json = null;
                        try {
                            json = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONArray jsonCandidates = (JSONArray)json.get("candidates");
                            candidates = new ArrayList<Candidate>(4);


                            for (int i = 0; i < jsonCandidates.length(); ++i) {
                                JSONObject c_list = jsonCandidates.getJSONObject(i);
                                JSONObject candidate_1 = jsonCandidates.getJSONObject(i);
                                String candidate_1_name = candidate_1.getString("name");
                                String candidate_1_party = candidate_1.getString("party");
                                String candidate_1_votes = candidate_1.getString("votes");

                                Candidate candidate = new Candidate(candidate_1_name, candidate_1_party, Integer.parseInt(candidate_1_votes));

                                candidates.add(candidate);

//                                c_name.setText(candidate_1_name);
//                                c_party.setText(candidate_1_party);
//                                c_votes.setText(candidate_1_votes);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        c_name.setText("Couldn't Connect!");
                    }
                });
        queue.add(stringRequest);
    }
}
