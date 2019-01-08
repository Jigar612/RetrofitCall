package com.jigar.android.retrofitcall.Response;

import com.google.gson.annotations.SerializedName;
import com.jigar.android.retrofitcall.Models.Get_Candidate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public class GetCandidateResponse {

    @SerializedName("GetCandidateResult")
    private List<Get_Candidate>list_candidate;

    public List<Get_Candidate> getList_candidate() {
        return list_candidate;
    }

    public void setList_candidate(List<Get_Candidate> list_candidate) {
        this.list_candidate = list_candidate;
    }
}
