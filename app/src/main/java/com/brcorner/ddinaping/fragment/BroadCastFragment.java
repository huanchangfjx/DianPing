package com.brcorner.ddinaping.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brcorner.ddinaping.R;
import com.brcorner.ddinaping.activity.MessageActivity;
import com.brcorner.ddinaping.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BroadCastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BroadCastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BroadCastFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BroadCastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BroadCastFragment newInstance(String param1, String param2) {
        BroadCastFragment fragment = new BroadCastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BroadCastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.v("BroadCastFragment","onCreate");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtils.v("BroadCastFragment","onCreateView");

        return inflater.inflate(R.layout.fragment_broad_cast, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        LogUtils.v("BroadCastFragment","onAttach");

        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.v("BroadCastFragment", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.v("BroadCastFragment","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.v("BroadCastFragment","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.v("BroadCastFragment","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.v("BroadCastFragment","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.v("BroadCastFragment","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.v("BroadCastFragment","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.v("BroadCastFragment","onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
