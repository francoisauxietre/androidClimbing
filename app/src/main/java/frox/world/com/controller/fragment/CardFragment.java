package frox.world.com.controller.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import frox.world.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment implements View.OnClickListener {

    //liens entre le fragment et l'activity parent dans lequel le fragment est inclus
    private OnButtonClickedListener callback;

    public CardFragment() {
        // Required empty public constructor
    }

    //ajout de cet interface que chaque personne voulant utiliser le fragment devra implementer
    //contrat
    public interface OnButtonClickedListener{
        public void OnButtonClicked(View view);
    }

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_card, container, false);
        result.findViewById(R.id.fragment_main_button).setOnClickListener(this);
        return result;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    @Override
    public void onClick(View v) {
        callback.OnButtonClicked(v);
    }

    /**
     * pour le fragment , callback = lien entre parent (activity Card2Activity) et (CardFragment)
     *
     */


    private void createCallbackToParentActivity(){

        try {
            callback = (OnButtonClickedListener) getActivity();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassCastException(e.toString()+ R.string.errorImplementation);
        }
    }
}
