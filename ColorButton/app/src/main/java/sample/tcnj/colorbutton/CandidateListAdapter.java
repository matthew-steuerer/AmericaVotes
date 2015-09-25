package sample.tcnj.colorbutton;

import android.view.LayoutInflater;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by matthias on 9/22/15.
 */
public class CandidateListAdapter {

    Candidate candidate = getItem(position);

    if(view == null)
    {
        view = LayoutInflater.from(getContext()).inflate(R.layout.candidate_view, parent)
    }

    TextView candidateName = (TextView) view.findViewById(R.id.candidateName);
    TextView candidateParty = (TextView) view.findValueByID(R.id.candidateParty);
    TextView candidateVotes = (TextView) view.findValueById(R.id.candidateVotes);


}
