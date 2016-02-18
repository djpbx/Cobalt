package danski.cobalt.Home;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import danski.cobalt.R;
import danski.cobalt.adaptor.MatchListAdaptor;
import danski.cobalt.sql.HeroRetreiver;
import danski.cobalt.sql.ItemRetreiver;
import danski.cobalt.sql.MatchListRetreiver;
import danski.cobalt.sql.MatchRetreiver;
import danski.cobalt.sql.SQLManager;


public class home_matchhistory extends Fragment {

    public static home_matchhistory instance;

    Button b;
    Button grabitem;
    Button grabheroes;
    ListView lv;
    MatchListAdaptor mla;

   /* public home_matchhistory() {
        // Required empty public constructor
    }


    public static home_matchhistory newInstance() {
        home_matchhistory fragment = new home_matchhistory();
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_matchhistory, container, false);

        b = (Button) v.findViewById(R.id.button);
        grabitem = (Button) v.findViewById(R.id.button3) ;
        grabheroes = (Button) v.findViewById(R.id.button2);
        lv = (ListView) v.findViewById(R.id.listView);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchListRetreiver mlr = new MatchListRetreiver();
                mlr.RetreiveAsync();
            }
        });

        grabitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemRetreiver ir = new ItemRetreiver();
                ir.retreiveAsync();
            }
        });

        grabheroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeroRetreiver hr = new HeroRetreiver();
                hr.retreiveAsync();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    TextView matchid = (TextView) view.findViewById(R.id.item_match_small_id);
                    MatchRetreiver mr = new MatchRetreiver();
                    mr.retreiveAsync(Long.parseLong(matchid.getText().toString()));
                } catch (Exception e){

                }
            }
        });

        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState){
        SQLManager sm = new SQLManager(getContext());
        Cursor matches = sm.getAllMatches();
        if(matches.getCount() > 0){
            populateList();
        }
    }

    public void populateList(){
        SQLManager sm = new SQLManager(getContext());
        final Cursor allmatches = sm.getAllMatches();
        mla = new MatchListAdaptor(getContext(), allmatches, 0);
        lv.setAdapter(mla);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        SQLManager.instance.getDatabase().close();
    }

}
