package sample.tcnj.colorbutton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class candidatesAdaptor extends ArrayAdapter<Candidate>
{
//    ArrayAdapter<String> candidatesAdaptor = new ArrayAdapter<String>(this, android.R.layout.candidate_entry, candidates);
    public candidatesAdaptor(Context context, List<Candidate> candidates)
    {
        super(context, 0, candidates);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        Candidate candidate = getItem(position);

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.candidate_entry, parent, false);
        }

        TextView candidateName = (TextView) view.findViewById(R.id.c_name);
        TextView candidateParty = (TextView) view.findViewById(R.id.c_party);
        TextView candidateVotes = (TextView) view.findViewById(R.id.c_votes);

        candidateName.setText(candidate.getName());
        candidateParty.setText(candidate.getParty());
        candidateVotes.setText(Integer.toString(candidate.getVotes()));

        return view;
    }


}
